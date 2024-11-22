package com.example.userservice.service;

import com.example.userservice.domain.Client;
import com.example.userservice.domain.Employee;
import com.example.userservice.exceptions.BaseException;
import com.example.userservice.exceptions.Messages;
import com.example.userservice.global.UserState;
import com.example.userservice.repository.ClientRepository;
import com.example.userservice.templates.MessageToSend;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class ClientService {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    private  ObjectWriter ow ;

    private final EmployeeService employeeService;

    private  ObjectMapper objectMapper ;

    private final ClientRepository clientRepository;


    public ClientService(ClientRepository clientRepository, EmployeeService employeeService) {
        this.clientRepository = clientRepository;
        this.objectMapper= new ObjectMapper();
        this.ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        this.employeeService = employeeService;
    }

    public void validPassword(String password) throws BaseException {
        if(password == null)
            throw new BaseException(Messages.PASSWORD);

        if(password == "" || password == " ")
            throw new BaseException(Messages.PASSWORD);
    }

    public Client save(Client entityToInsert, String password) throws BaseException {
       Optional<Client> user = getClientByUsername(entityToInsert.getUsername());
        if (user.isPresent())
            throw new BaseException(Messages.USER_EXISTS);


        if (entityToInsert.getTypeClient() == null)
            throw new BaseException(Messages.TYPE_USER);

        validPassword(password);
        entityToInsert.setPassword(password);

        entityToInsert.setUserState(UserState.WAITING_FOR_REGISTRATION_CONFIRMATION);
        Client clientInserted = clientRepository.save(entityToInsert);
        sendMessageToSaveUserForAuthentication(clientInserted, password);
        return clientInserted;
    }

    public Client updateClient(UUID uuid, Client clientToInsert) throws BaseException {
        Optional<Client> client = clientRepository.findById(uuid);
        if (!client.isPresent()) {
            throw new BaseException(Messages.USER_NOT_FOUND);
        }
        //client.get().setUserState(clientToInsert.getUserState());
        client.get().setEmail(clientToInsert.getEmail() != null ? clientToInsert.getEmail() : client.get().getEmail());
        client.get().setFirstName(clientToInsert.getFirstName() != null ? clientToInsert.getFirstName() : client.get().getFirstName());
        client.get().setLastName(clientToInsert.getLastName() != null ? clientToInsert.getLastName() : client.get().getLastName());
        return clientRepository.save(client.get());
    }

    public void deleteClient(UUID uuid) throws BaseException {
        Optional<Client> user = clientRepository.findById(uuid);
        if (!user.isPresent()) {
            throw new BaseException(Messages.USER_NOT_FOUND);
        }
        user.get().setUserState(UserState.REMOVED);
        sendMessageToSaveDisableUser(user.get());
        clientRepository.save(user.get());
    }

    public Optional<Client> getById(UUID uuid) {
        return clientRepository.findById(uuid);
    }
    public Optional<Client> getClientByUsername(String username) {
        return clientRepository.findByUsername(username);
    }

    public Page<Client> getClients(Pageable pageable) {
        return clientRepository.findAll( pageable);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @RabbitListener(queues = "user_service_queue")
    public void listenerMessagesOfQueue(String message) throws JsonProcessingException {
        System.out.println("Message Received from Auth Service.... ");
        Boolean isValid = false;
        JsonNode messageReceived = null;
        try {
            messageReceived = objectMapper.readTree(message);
            isValid = true;
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        if(messageReceived != null)
            messageReceived(messageReceived);
    }

    public void messageReceived(JsonNode messageReceived){
        if(messageReceived.get("typeUser").textValue().equals("CLIENT")) {
            Optional<Client> client = getById(UUID.fromString(messageReceived.get("userId").textValue()));
            if(messageReceived.get("typeResponse").textValue().equals("CREATED") && client.isPresent()){
                client.get().setUserState(UserState.REGISTERED);
                clientRepository.save(client.get());
            }else if(messageReceived.get("typeResponse").textValue().equals("FAIL") && client.isPresent()){
                clientRepository.delete(client.get());
            }
        }else if((messageReceived.get("typeUser").textValue().equals("WORKER"))){
            Optional<Employee> employee = employeeService.getById(UUID.fromString(messageReceived.get("userId").textValue()));
            if(messageReceived.get("typeResponse").textValue().equals("CREATED") && employee.isPresent()){
                employee.get().setUserState(UserState.REGISTERED);
                employeeService.confirmEmployee(employee.get());
            }else if(messageReceived.get("typeResponse").textValue().equals("FAIL") && employee.isPresent()){
                employeeService.deleteEmployee(employee.get());
            }
        }else if((messageReceived.get("typeUser").textValue().equals("ADMIN"))){
            Optional<Employee> employee = employeeService.getById(UUID.fromString(messageReceived.get("userId").textValue()));
            if(messageReceived.get("typeResponse").textValue().equals("CREATED") && employee.isPresent()){
                employee.get().setUserState(UserState.REGISTERED);
                employeeService.confirmEmployee(employee.get());
            }else if(messageReceived.get("typeResponse").textValue().equals("FAIL") && employee.isPresent()){
                employeeService.deleteEmployee(employee.get());
            }
        }
    }
    public void sendMessageToSaveUserForAuthentication(Client client, String password) {
        MessageToSend messageToSend = new MessageToSend();
        messageToSend.setUserId(client.getId().toString());
        messageToSend.setPassword(password);
        messageToSend.setTypeOperation("SAVE");
        messageToSend.setUsername(client.getUsername());
        messageToSend.setTypeUser("CLIENT");

        try {
            System.out.println("Sending message to Auth service...");
            this.rabbitTemplate.convertAndSend("auth_service_queue_exchange", "auth_service_queue_routingKey",ow.writeValueAsString(messageToSend));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessageToSaveDisableUser(Client client) {
        MessageToSend messageToSend = new MessageToSend();
        messageToSend.setUserId(client.getId().toString());
        messageToSend.setTypeOperation("DELETE");
        messageToSend.setUsername(client.getUsername());
        messageToSend.setTypeUser("CLIENT");
        try {
            System.out.println("Sending message to Auth service...");
            this.rabbitTemplate.convertAndSend("auth_service_queue_exchange", "auth_service_queue_routingKey",ow.writeValueAsString(messageToSend));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
