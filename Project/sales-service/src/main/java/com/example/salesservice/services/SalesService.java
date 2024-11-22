package com.example.salesservice.services;

import com.example.salesservice.domain.Sale;
import com.example.salesservice.exceptions.BaseException;
import com.example.salesservice.global.SaleState;
import com.example.salesservice.predicate.SalePredicatesBuilder;
import com.example.salesservice.repository.SaleRepository;
import com.example.salesservice.templates.MessageSend;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class SalesService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private ObjectWriter ow ;

    private ObjectMapper objectMapper ;

    private final SaleRepository saleRepository;

    private String enumQuery = "";

    @Autowired
    public SalesService(SaleRepository saleRepository){
        this.saleRepository = saleRepository;
        this.objectMapper= new ObjectMapper();
        this.ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    }
    public Sale save(Sale entityToInsert, UUID itemCar) throws BaseException {
        entityToInsert.setDate(ZonedDateTime.now());
        entityToInsert.setItemCar(itemCar);
        entityToInsert.setState(SaleState.AWAITS_CONFIRMATION);
        Sale itemCarInserted = saleRepository.save(entityToInsert);
        sendMessageToAdvertisingService(itemCarInserted);
        return itemCarInserted;
    }

    public List<Sale> getSale(String search, Pageable pageable) {
        List<Sale> listSales = null;
        if(search != null && search.contains("client")){
            listSales = saleRepository.findAllByClient(UUID.fromString(search.split(":")[1].split(",")[0]));
        }
        BooleanExpression exp = initBuilder(search).build();
        System.out.println("Exp "  + exp);
        Page<Sale> page = null;
        if(exp != null){
            page = saleRepository.findAll(exp,pageable);
        }else{
            page = saleRepository.findAll(pageable);
        }
        return  filterByEnum(page,listSales);
    }


    public List<Sale> filterByEnum (Page<Sale> page, List<Sale> sales) {
        if (enumQuery.equals("") && sales == null) {
            return page.getContent();
        }else if(enumQuery.equals("") && sales != null){
            return sales;
        }

        String[] listEnums = enumQuery.split(";");
        List<Sale> filteredSales = new ArrayList<>();
        if (listEnums.length == 1) {
            String value = listEnums[0].split(":")[1];
            if (listEnums[0].contains("state")) {
                for (Sale car : page.getContent()) {
                    if (car.getState().toString().equals(value))
                        filteredSales.add(car);
                }
            }
        }
        List<Sale> filteredSalesWithClient = new ArrayList<>();
        if(sales != null){
            for(Sale sale : sales){
                Sale saleFinded = filteredSales.stream().filter(sale1 -> sale1.getId().equals(sale.getId())).findFirst().orElse(null);
                if(saleFinded != null){
                    filteredSalesWithClient.add(sale);
                }
            }
            return filteredSalesWithClient;
        }

        return filteredSales;
    }
    public SalePredicatesBuilder initBuilder (String search){
        enumQuery = "";
        SalePredicatesBuilder builder = new SalePredicatesBuilder();
        if (search != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                if(!matcher.group(1).equals("state")){
                    builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
                }else{
                    enumQuery +=  matcher.group(1) + matcher.group(2) +  matcher.group(3) + ";" ;
                }
            }
        }
        return builder;
    }
    public Optional<Sale> getById(UUID uuid) {
        return saleRepository.findById(uuid);
    }

    public void sendMessageToAdvertisingService(Sale sale){
        MessageSend messageToSend = new MessageSend();
        messageToSend.setSender("SALES-SERVICE");
        messageToSend.setItemCarId(sale.getItemCar().toString());
        messageToSend.setOperationType("CONFIRM");
        messageToSend.setIdSale(sale.getId().toString());
        try {
            System.out.println("Sending message to Cardissambly service...");
            this.rabbitTemplate.convertAndSend("cardisassembly_service_queue_exchange", "cardisassembly_service_routingKey",ow.writeValueAsString(messageToSend));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @RabbitListener(queues = "sales_service_queue")
    public void listenerMessagesOfQueue(String message) {
        System.out.println("Message Received Purchase service.... ");
        Boolean isValid = false;
        JsonNode messageReceived = null;
        try {
            messageReceived = objectMapper.readTree(message);
            isValid = true;
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Recebi a mensagem para confirmar ");

        System.out.println("Mensagem " + messageReceived);


        if( messageReceived.get("sender").textValue().equals("CARDISASSEMBLY-SERVICE")){
            Optional<Sale> sale = getById(UUID.fromString(messageReceived.get("saleId").textValue()));
            if(sale.isPresent() && messageReceived.get("typeOperation").textValue().equals("CONFIRM")){
                sale.get().setState(SaleState.CONFIRMED);
            } else if (sale.isPresent() && messageReceived.get("typeOperation").textValue().equals("CANCEL")) {
                sale.get().setState(SaleState.CANCELED);
            }
            if(sale.isPresent()){
                saleRepository.save(sale.get());
            }
        }

    }


}
