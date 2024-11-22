package com.example.storageservice.service;

import com.example.storageservice.domain.Section;
import com.example.storageservice.exceptions.BaseException;
import com.example.storageservice.repository.SectionRepository;
import com.example.storageservice.repository.StorageRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class SectionService {

    private SectionRepository storageRepository;

    private ObjectMapper objectMapper ;


    @Autowired
    public SectionService(SectionRepository saleRepository){
        this.storageRepository = saleRepository;
        this.objectMapper= new ObjectMapper();
    }

    public List<Section>  findAll(){
     return storageRepository.findAll();
    }

    public Optional<Section> findByCategory(Long category){
        return storageRepository.findByCategory(category);
    }

    public void incrementPosition(Long uuid) {
        Optional<Section> item = storageRepository.findByCategory(uuid);
        if(item.isPresent()){
            System.out.println("getFreePositions"  + item.get().getFreePositions());
            if(item.get().getFreePositions() < 25){
                item.get().setFreePositions(item.get().getFreePositions() + 1);
                storageRepository.save(item.get());
            }
        }
    }

    public void decrementPosition(Long uuid)  {
        Optional<Section> item = storageRepository.findByCategory(uuid);
        if(item.isPresent()){
            if( item.get().getFreePositions() > 0){
                System.out.println("getFreePositions"  + item.get().getFreePositions());
                item.get().setFreePositions(item.get().getFreePositions() - 1);
                storageRepository.save(item.get());
            }
        }
    }
    @RabbitListener(queues = "storage_service_queue")
    public void listenerMessagesOfQueue(String message) {
        System.out.println("Message Received Dissambley service.... ");
        Boolean isValid = false;
        JsonNode messageReceived = null;
        try {
            messageReceived = objectMapper.readTree(message);
            isValid = true;
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        if (messageReceived.get("action").textValue().equals("INCREMENT")) {
            System.out.println("Increment");
            incrementPosition(Long.valueOf(messageReceived.get("category").toString()));
        }else {
            System.out.println("Remove");
            decrementPosition(Long.valueOf(messageReceived.get("category").toString()));
        }


    }

}
