package com.example.userservice.init;


import com.example.userservice.domain.Client;
import com.example.userservice.global.UserState;
import com.example.userservice.service.ClientService;
import com.example.userservice.templates.MessageToSend;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;


@Component
public class CheckMissedMessages {


  private final ClientService clientService;


  private final RabbitTemplate rabbitTemplate;


  private ObjectWriter ow ;

  @Autowired
  public CheckMissedMessages(ClientService clientService, RabbitTemplate rabbitTemplate){
    this.clientService = clientService;
    this.rabbitTemplate = rabbitTemplate;
    this.ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
  }


  @Scheduled(cron = "0 */30 * * * ?") // once a day
  public void checkMissedMessages() {
    List<Client> clients = clientService.findAll();
    for(Client c : clients){
      if(c.getUserState() == UserState.WAITING_FOR_REGISTRATION_CONFIRMATION){
        sendMessageToSaveUserForAuthentication(c,c.getPassword());
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


}
