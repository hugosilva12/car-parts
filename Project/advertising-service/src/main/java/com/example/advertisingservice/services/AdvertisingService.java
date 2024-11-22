package com.example.advertisingservice.services;

import com.example.advertisingservice.domain.Advertising;
import com.example.advertisingservice.exceptions.BaseException;
import com.example.advertisingservice.exceptions.Messages;
import com.example.advertisingservice.predicate.AdvertisingPredicatesBuilder;
import com.example.advertisingservice.repository.AdvertisingRepository;
import com.example.advertisingservice.templates.MessageToSend;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sound.midi.Soundbank;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class AdvertisingService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private ObjectWriter ow;

    private ObjectMapper objectMapper;

    private final AdvertisingRepository advertisingRepository;

    @Autowired
    public AdvertisingService(AdvertisingRepository advertisingRepository) {
        this.advertisingRepository = advertisingRepository;
        this.objectMapper = new ObjectMapper();
        this.ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    }

    public Advertising save(Advertising entityToInsert) throws BaseException {
        Optional<Advertising> advertising = advertisingRepository.findByItemCar(entityToInsert.getItemCar());
        if (advertising.isPresent()) {
            throw new BaseException(Messages.ADVERTISING_EXISTS);
        }

        Advertising itemCarInserted = advertisingRepository.save(entityToInsert);
        //sendMessageToPurchaseService(itemCarInserted);
        return itemCarInserted;
    }

    public List<Advertising> getAdvertising(String search, Pageable pageable) {
        BooleanExpression exp = initBuilder(search).build();

        Page<Advertising> page = null;
        if (exp != null) {
            page = advertisingRepository.findAll(exp, pageable);
        } else {
            page = advertisingRepository.findAll(pageable);
        }
        return page.getContent();
    }

    public Advertising updateAdvertising(Long uuid, Advertising advertisingToUpdate) throws BaseException {
        Optional<Advertising> advertising = advertisingRepository.findById(uuid);
        if (!advertising.isPresent()) {
            throw new BaseException(Messages.ADVERTISING_NOT_FOUND);
        }
        advertising.get().setPrice(advertisingToUpdate.getPrice() != null ? advertisingToUpdate.getPrice() : advertising.get().getPrice());
        advertising.get().setPhotoPath(advertisingToUpdate.getPhotoPath() != null ? advertisingToUpdate.getPhotoPath() : advertising.get().getPhotoPath());
        return advertisingRepository.save(advertising.get());
    }

    public void deleteAdvertising(Long uuid) throws BaseException {
        Optional<Advertising> advertising = advertisingRepository.findById(uuid);
        if (!advertising.isPresent()) {
            throw new BaseException(Messages.ADVERTISING_NOT_FOUND);
        }
        advertisingRepository.delete(advertising.get());
    }

    public Optional<Advertising> getById(Long uuid) {
        return advertisingRepository.findById(uuid);
    }

    public Optional<Advertising> findByItemCarId(UUID itemCar) {
        return advertisingRepository.findByItemCar(itemCar);
    }

    public AdvertisingPredicatesBuilder initBuilder(String search) {
        AdvertisingPredicatesBuilder builder = new AdvertisingPredicatesBuilder();
        if (search != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                if (!matcher.group(1).equals("fuelType") && !matcher.group(1).equals("gear") && !matcher.group(1).equals("carState")) {
                    builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
                }
            }
        }
        return builder;
    }

    @RabbitListener(queues = "advertising_service_queue")
    public void listenerMessagesOfQueue(String message) {
        System.out.println("Message Received Cardisassembly service.... ");
        Boolean isValid = false;
        JsonNode messageReceived = null;
        try {
            messageReceived = objectMapper.readTree(message);
            isValid = true;
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        if (isValid) {
            if (messageReceived.get("typeMessage").textValue().equals("INSERT")) {
                Advertising advertising = new Advertising();
                advertising.setPhotoPath(messageReceived.get("photoPath").textValue());
                advertising.setItemState(messageReceived.get("itemState").textValue());
                advertising.setCategory(messageReceived.get("category").textValue());
                advertising.setItemCar(UUID.fromString(messageReceived.get("itemCar").textValue()));
                advertising.setPrice(convertStringToBigDecimal(messageReceived.get("price").toString()));
                advertising.setDescription(messageReceived.get("description").textValue());
                try {
                    save(advertising);
                    sendMessageToCardisassembly(advertising.getItemCar().toString());
                } catch (BaseException e) {
                    System.out.println("Error ");
                }

            } else if (messageReceived.get("typeMessage").textValue().equals("DELETE")) {
                Optional<Advertising> optionalAdvertising = findByItemCarId(UUID.fromString(messageReceived.get("itemCar").textValue()));
                if (optionalAdvertising.isPresent()) {
                    advertisingRepository.delete(optionalAdvertising.get());
                }
            }

        }

    }

    public void sendMessageToCardisassembly(String itemCarId) {
        MessageToSend messageToSend = new MessageToSend();
        messageToSend.setItemCarId(itemCarId);
        messageToSend.setSender("ADVERTISING-SERVICE");
        messageToSend.setTypeResponse("ADVERTISED");
        try {
            System.out.println("Sending message to Cardisassembly service...");
            this.rabbitTemplate.convertAndSend("cardisassembly_service_queue_exchange", "cardisassembly_service_routingKey", ow.writeValueAsString(messageToSend));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private BigDecimal convertStringToBigDecimal(String value) {

        BigDecimal valueDecimal = new BigDecimal(value);
        return valueDecimal;
    }
}
