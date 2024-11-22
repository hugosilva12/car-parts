package com.example.userservice.service;

import com.example.userservice.domain.Employee;
import com.example.userservice.exceptions.BaseException;
import com.example.userservice.exceptions.Messages;
import com.example.userservice.global.UserState;
import com.example.userservice.repository.EmployeeRepository;
import com.example.userservice.templates.MessageToSend;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
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
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private ObjectWriter ow ;


    private ObjectMapper objectMapper ;


    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.objectMapper= new ObjectMapper();
        this.ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    }

    public void validPassword(String password) throws BaseException {
        if(password == null)
            throw new BaseException(Messages.PASSWORD);

        if(password == "" || password == " ")
            throw new BaseException(Messages.PASSWORD);
    }
    public Employee save(Employee entityToInsert, String password) throws BaseException {
        Optional<Employee> user = getEmployeeByUsername(entityToInsert.getUsername());
        if (user.isPresent())
            throw new BaseException(Messages.USER_EXISTS);

        if (entityToInsert.getTypeEmployee() == null)
            throw new BaseException(Messages.TYPE_USER);

        validPassword(password);
        entityToInsert.setUserState(UserState.WAITING_FOR_REGISTRATION_CONFIRMATION);
        Employee employee = employeeRepository.save(entityToInsert);
        sendMessageToSaveUserForAuthentication(employee, password);
        return employeeRepository.save(entityToInsert);
    }


    public Employee updateEmployee(UUID uuid, Employee employeeToInsert) throws BaseException {
        Optional<Employee> employee = employeeRepository.findById(uuid);
        if (!employee.isPresent()) {
            throw new BaseException(Messages.USER_NOT_FOUND);
        }

        employee.get().setEmail(employeeToInsert.getEmail() != null ? employeeToInsert.getEmail() :  employee.get().getEmail());
        employee.get().setFirstName(employeeToInsert.getFirstName() != null ? employeeToInsert.getFirstName() : employee.get().getFirstName());
        employee.get().setLastName(employeeToInsert.getLastName() != null ? employeeToInsert.getLastName() :  employee.get().getLastName());


        return employeeRepository.save(employee.get());
    }

    public void deleteEmployee(UUID uuid) throws BaseException {
        Optional<Employee> user = employeeRepository.findById(uuid);
        if (!user.isPresent()) {
            throw new BaseException(Messages.USER_NOT_FOUND);
        }
        user.get().setUserState(UserState.REMOVED);
        sendMessageToSaveDisableUser(user.get());
        employeeRepository.save(user.get());
    }

    public Optional<Employee> getById(UUID uuid) {
        return employeeRepository.findById(uuid);
    }
    public Optional<Employee> getEmployeeByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

    public Page<Employee> getUsers(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public void confirmEmployee(Employee entityToInsert)  {
        employeeRepository.save(entityToInsert);
    }
    public void deleteEmployee(Employee entityToInsert)  {
        employeeRepository.delete(entityToInsert);
    }

    public void sendMessageToSaveUserForAuthentication(Employee employee, String password) {
        MessageToSend messageToSend = new MessageToSend();
        messageToSend.setUserId(employee.getId().toString());
        messageToSend.setPassword(password);
        messageToSend.setTypeOperation("SAVE");
        messageToSend.setUsername(employee.getUsername());
        messageToSend.setTypeUser(employee.getTypeEmployee().toString());
        try {
            System.out.println("Sending message to Auth service...");
            this.rabbitTemplate.convertAndSend("auth_service_queue_exchange", "auth_service_queue_routingKey",ow.writeValueAsString(messageToSend));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessageToSaveDisableUser(Employee employee) {
        MessageToSend messageToSend = new MessageToSend();
        messageToSend.setUserId(employee.getId().toString());
        messageToSend.setTypeOperation("DELETE");
        messageToSend.setUsername(employee.getUsername());
        messageToSend.setTypeUser(employee.getTypeEmployee().toString());
        try {
            System.out.println("Sending message to Auth service...");
            this.rabbitTemplate.convertAndSend("auth_service_queue_exchange", "auth_service_queue_routingKey",ow.writeValueAsString(messageToSend));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
