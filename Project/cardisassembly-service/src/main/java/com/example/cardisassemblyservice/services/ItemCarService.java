package com.example.cardisassemblyservice.services;

import com.example.cardisassemblyservice.domain.Category;
import com.example.cardisassemblyservice.domain.ItemCar;
import com.example.cardisassemblyservice.domain.ItemCarState;
import com.example.cardisassemblyservice.exceptions.BaseException;
import com.example.cardisassemblyservice.global.enums.ItemCarLifecycle;
import com.example.cardisassemblyservice.predicate.ItemCarPredicatesBuilder;
import com.example.cardisassemblyservice.repository.CategoryRepository;
import com.example.cardisassemblyservice.repository.ItemCarRepository;
import com.example.cardisassemblyservice.repository.ItemStateRepository;
import com.example.cardisassemblyservice.templates.*;
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

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class ItemCarService {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    private ObjectWriter ow;

    private ObjectMapper objectMapper;

    private final ItemCarRepository itemCarRepository;

    private String enumQuery = "";

    private final ItemStateRepository itemStateRepository;

    private final CategoryService categoryService;

    @Autowired
    public ItemCarService(ItemCarRepository itemCarRepository, ItemStateRepository itemStateRepository,
                          CategoryService categoryRepository) {
        this.itemCarRepository = itemCarRepository;
        this.objectMapper = new ObjectMapper();
        this.ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        this.itemStateRepository = itemStateRepository;
        this.categoryService = categoryRepository;
    }

    public ItemCar save(ItemCar entityToInsert, ItemCarState itemState, Category category, String description) throws BaseException {
        entityToInsert.setItemCarLifecycle(ItemCarLifecycle.AWAITING_REGISTRATION_CONFIRMATION);
        entityToInsert.setItemState(itemState);
        entityToInsert.setCategory(category);
        entityToInsert.setDescription(description);
        entityToInsert.setDate(ZonedDateTime.now());
        ItemCar itemCarInserted = itemCarRepository.save(entityToInsert);
        categoryService.incrementStock(itemCarInserted.getCategory().getId());
        sendMessageToPurchaseService(itemCarInserted);
        return itemCarInserted;
    }

    public ItemCar updateItemCar(UUID uuid, ItemCar itemCarToUpdate) throws BaseException {
        Optional<ItemCar> itemCar = itemCarRepository.findById(uuid);
        if (!itemCar.isPresent()) {
            throw new BaseException(com.example.cardisassemblyservice.exceptions.Messages.ITEM_CAR_NOT_FOUND);
        }
        itemCar.get().setPrice(itemCarToUpdate.getPrice() != null ? itemCarToUpdate.getPrice() : itemCar.get().getPrice());
        return itemCarRepository.save(itemCar.get());
    }

    public void deleteItemCar(UUID uuid) throws BaseException {
        Optional<ItemCar> itemCar = itemCarRepository.findById(uuid);
        if (!itemCar.isPresent()) {
            throw new BaseException(com.example.cardisassemblyservice.exceptions.Messages.ITEM_CAR_NOT_FOUND);
        }

        sendMessageToAdvertisingServiceForDelete(itemCar.get());
        itemCarRepository.delete(itemCar.get());
    }

    public Optional<ItemCar> getById(UUID uuid) {
        Optional<ItemCar> itemCar = itemCarRepository.findById(uuid);
        return itemCar;
    }


    public List<ItemCar> getCars(String search, Pageable pageable) {
        BooleanExpression exp = initBuilder(search).build();

        Page<ItemCar> page = null;
        if (exp != null) {
            page = itemCarRepository.findAll(exp, pageable);
        } else {
            page = itemCarRepository.findAll(pageable);
        }
        return filterByEnum(page);
    }

    public List<ItemCar> filterByEnum(Page<ItemCar> page) {
        List<ItemCarState> list = itemStateRepository.findAll();
        for (ItemCar itemCar : page.getContent()) {
            itemCar.setItemState(list.stream().filter(specie -> itemCar.getItemState().getId().equals(specie.getId())).findFirst().orElse(null));
        }

        if (enumQuery.equals("")) {
            return page.getContent();
        }
        return null;
    }

    public ItemCarPredicatesBuilder initBuilder(String search) {
        ItemCarPredicatesBuilder builder = new ItemCarPredicatesBuilder();
        enumQuery = "";
        if (search != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                if (!matcher.group(1).equals("fuelType") && !matcher.group(1).equals("gear") && !matcher.group(1).equals("carState")) {
                    builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
                } else {
                    enumQuery += matcher.group(1) + matcher.group(2) + matcher.group(3) + ";";
                }
            }
        }
        return builder;
    }

    public void sendMessageToPurchaseService(ItemCar itemCarInserted) {
        MessageToSend messageToSend = new MessageToSend();
        messageToSend.setCarId(itemCarInserted.getCarId().toString());
        messageToSend.setTypeOperation("VERIFY_IF_EXISTS");
        messageToSend.setItemCarId(itemCarInserted.getId().toString());
        try {
            System.out.println("Sending message to Purchase service...");
            this.rabbitTemplate.convertAndSend("purchase_service_queue_exchange", "purchase_service_routingKey", ow.writeValueAsString(messageToSend));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessageToAdvertisingService(ItemCar itemCarInserted) {
        MessageToCreateAdvertising messageToSend = new MessageToCreateAdvertising();

        messageToSend.setCategory(itemCarInserted.getCategory().getName());
        messageToSend.setPhotoPath(itemCarInserted.getPhotoPath());
        messageToSend.setTypeMessage("INSERT");
        messageToSend.setPrice(itemCarInserted.getPrice());
        messageToSend.setItemCar(itemCarInserted.getId().toString());
        messageToSend.setItemState(itemCarInserted.getItemState().getDescription());
        messageToSend.setDescription(itemCarInserted.getDescription());
        try {
            System.out.println("Sending message to Advertising service...");
            this.rabbitTemplate.convertAndSend("advertising_service_queue_exchange", "advertising_service_routingKey", ow.writeValueAsString(messageToSend));
        } catch (JsonProcessingException e) {
            System.out.println("ERROR: "  + e);
        }
    }

    public void sendMessageToAdvertisingServiceForDelete(ItemCar itemCar) {
        MessageToCreateAdvertising messageToSend = new MessageToCreateAdvertising();
        messageToSend.setTypeMessage("DELETE");
        messageToSend.setItemCar(itemCar.getId().toString());
        try {
            System.out.println("Sending message to Advertising service...");
            this.rabbitTemplate.convertAndSend("advertising_service_queue_exchange", "advertising_service_routingKey", ow.writeValueAsString(messageToSend));
        } catch (JsonProcessingException e) {
            System.out.println("ERROR: "  + e);
        }
    }

    public void sendMessageToStoreService(Long category, String message) {
        MessageToStorage messageToSend = new MessageToStorage();
        messageToSend.setAction(message);
        messageToSend.setCategory(category);
        try {
            System.out.println("Sending message to Store service...");
            this.rabbitTemplate.convertAndSend("storage_service_queue_exchange", "storage_service_routingKey", ow.writeValueAsString(messageToSend));
        } catch (JsonProcessingException e) {
            System.out.println("ERROR: "  + e);
        }
    }

    public void sendMessageToSalesService(String sender, String saleId, String typeOperation) {
        MessageToSendSalesService messageToSend = new MessageToSendSalesService();
        messageToSend.setSender(sender);
        messageToSend.setTypeOperation(typeOperation);
        messageToSend.setSaleId(saleId);
        try {
            System.out.println("Sending message to Sales service...");
            this.rabbitTemplate.convertAndSend("sales_service_queue_exchange", "sales_service_routingKey", ow.writeValueAsString(messageToSend));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessageToPrecauriousService(ItemCar itemCar){
        MessageToSendPrecauriousService message = new MessageToSendPrecauriousService();
        message.setTypeMessage("ITEM_CAR_PRICE");
        message.setItemStateId(itemCar.getItemState().getId());
        message.setDescription(itemCar.getDescription());
        message.setCategoryId(itemCar.getCategory().getId());
        message.setPrice(itemCar.getPrice());
        message.setCarId(itemCar.getCarId().toString());
        try {
            System.out.println("Sending message to Precaurious service...");
            this.rabbitTemplate.convertAndSend("precarious_service_queue_exchange", "precarious_service_routingKey", ow.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    @RabbitListener(queues = "cardisassembly_service_queue")
    public void listenerMessagesOfQueue(String message) {
        Boolean isValid = false;
        JsonNode messageReceived = null;
        try {
            messageReceived = objectMapper.readTree(message);
            isValid = true;
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }

        Optional<ItemCar> itemCar = null;

        if(messageReceived.get("itemCarId") != null){
            itemCar = getById(UUID.fromString(messageReceived.get("itemCarId").textValue()));
        }

        System.out.println("Item car " + itemCar.get());

        if(!itemCar.isPresent()){
            System.out.println("Not Present" );
            return;
        }
        if(messageReceived.get("typeResponse") != null) {
            System.out.println("Message Received Purchase service.... ");
            if (messageReceived.get("typeResponse").textValue().equals("NOT_FOUND") && messageReceived.get("sender").textValue().equals("PURCHASE-SERVICE")) {
                itemCar.get().setItemCarLifecycle(ItemCarLifecycle.INVALID_REGISTRATION);
                itemCarRepository.save(itemCar.get());
            } else if (messageReceived.get("typeResponse").textValue().equals("FOUND") && messageReceived.get("sender").textValue().equals("PURCHASE-SERVICE")) {
                itemCar.get().setItemCarLifecycle(ItemCarLifecycle.REGISTERED);
                itemCarRepository.save(itemCar.get());
                Optional<ItemCarState> itemCarState = itemStateRepository.findById(itemCar.get().getItemState().getId());
                if(itemCarState.isPresent() && itemCarState.get().getPercentage() >= 50 ){
                    sendMessageToAdvertisingService(itemCar.get());
                    sendMessageToStoreService(itemCar.get().getCategory().getId(),"REMOVE");
                }else{
                    System.out.println("Peça registada que não é para venda");
                }
            } else if (messageReceived.get("typeResponse").textValue().equals("ADVERTISED") && messageReceived.get("sender").textValue().equals("ADVERTISING-SERVICE")) {
                System.out.println("Message Received Advertising service.... ");
                itemCar.get().setItemCarLifecycle(ItemCarLifecycle.ADVERTISED);
                itemCarRepository.save(itemCar.get());
            }
                //Test if advertising is removed when itemcar is sold
        }else if( messageReceived.get("sender").textValue().equals("SALES-SERVICE")){
                if(itemCar.get().getItemCarLifecycle().equals(ItemCarLifecycle.REGISTERED) || itemCar.get().getItemCarLifecycle().equals(ItemCarLifecycle.ADVERTISED)){
                    itemCar.get().setItemCarLifecycle(ItemCarLifecycle.SOLD);
                    try {
                        categoryService.decrementStock(itemCar.get().getCategory().getId());
                        sendMessageToSalesService("CARDISASSEMBLY-SERVICE",messageReceived.get("idSale").textValue(),"CONFIRM");
                        sendMessageToPrecauriousService(itemCar.get());
                        sendMessageToAdvertisingServiceForDelete(itemCar.get());
                        sendMessageToStoreService(itemCar.get().getCategory().getId(),"INCREMENT");
                    } catch (BaseException e) {
                        System.out.println("Error");
                    //sendMessageToSalesService("CARDISASSEMBLY-SERVICE",messageReceived.get("idSale").textValue(),"CANCEL");
                    }
                } else if(itemCar.get().getItemCarLifecycle().equals(ItemCarLifecycle.INVALID_REGISTRATION) || itemCar.get().getItemCarLifecycle().equals(ItemCarLifecycle.AWAITING_REGISTRATION_CONFIRMATION) || itemCar.get().getItemCarLifecycle().equals(ItemCarLifecycle.SOLD) ){
                    sendMessageToSalesService("CARDISASSEMBLY-SERVICE",messageReceived.get("idSale").textValue(),"CANCEL");
                }

        }

    }

}
