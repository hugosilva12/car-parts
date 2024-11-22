package com.example.purchaseservice.services;

import com.example.purchaseservice.domain.Car;
import com.example.purchaseservice.exceptions.BaseException;
import com.example.purchaseservice.exceptions.Messages;
import com.example.purchaseservice.global.enums.CarState;
import com.example.purchaseservice.predicate.CarPredicatesBuilder;
import com.example.purchaseservice.repository.CarRepository;
import com.example.purchaseservice.templates.MessageToSend;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.common.collect.Lists;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
public class CarService {


    private final RabbitTemplate rabbitTemplate;

    private  ObjectWriter ow ;

    private  ObjectMapper objectMapper ;

    private final CarRepository carRepository;

    private String enumQuery = "";

    @Autowired
    public CarService(CarRepository CarRepository,RabbitTemplate rabbitTemplate) {
        this.carRepository = CarRepository;
        this.objectMapper= new ObjectMapper();
        this.ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        this.rabbitTemplate = rabbitTemplate;
    }


    public Car save(Car entityToInsert) throws BaseException {
        Optional<Car> user = getCarByVin(entityToInsert.getVin());
        if (user.isPresent())
            throw new BaseException(Messages.CAR_EXISTS);

        entityToInsert.setCarState(CarState.AWAITS_DISASSEMBLY);
        if(entityToInsert.getEntryDate() == null){
            entityToInsert.setEntryDate(ZonedDateTime.now());
        }
        Car carInserted = carRepository.save(entityToInsert);
        return carInserted;
    }

    public Car updateCar(UUID uuid, Car carToInsert,CarState carState) throws BaseException {
        Optional<Car> car = carRepository.findById(uuid);
        if (!car.isPresent()) {
            throw new BaseException(Messages.CAR_NOT_FOUND);
        }
        car.get().setModel(carToInsert.getModel() != null ? carToInsert.getModel() : car.get().getModel());
        car.get().setBrand(carToInsert.getBrand() != null ? carToInsert.getBrand() : car.get().getBrand());
        car.get().setEngine_capacity(carToInsert.getEngine_capacity() != null ? carToInsert.getEngine_capacity() : car.get().getEngine_capacity());
        car.get().setGear(carToInsert.getGear() != null ? carToInsert.getGear() : car.get().getGear());
        car.get().setKm(carToInsert.getKm() != null ? carToInsert.getKm() : car.get().getKm());
        car.get().setHp(carToInsert.getHp() != null ? carToInsert.getHp() : car.get().getHp());
        car.get().setPrice(carToInsert.getPrice() != null ? carToInsert.getPrice() : car.get().getPrice());
        car.get().setYear(carToInsert.getYear() != null ? carToInsert.getYear() : car.get().getYear());
        car.get().setFuelType(carToInsert.getFuelType() != null ? carToInsert.getFuelType() : car.get().getFuelType());
        car.get().setCarState(carState != null ? carState : car.get().getCarState());
        System.out.println("Car state " + car.get().getCarState());
        return carRepository.save(car.get());
    }

    public void deleteCar(UUID uuid) throws BaseException {
        Optional<Car> car = carRepository.findById(uuid);
        if (!car.isPresent()) {
            throw new BaseException(Messages.CAR_NOT_FOUND);
        }
        car.get().setCarState(CarState.REMOVED);
        carRepository.save(car.get());
    }

    public Optional<Car> getById(UUID uuid) {
        return carRepository.findById(uuid);
    }
    public Optional<Car> getCarByVin(String vin) {
        return carRepository.findByVin(vin);
    }

    public List<Car> getCars(String search, Pageable pageable) {
        BooleanExpression exp = initBuilder(search).build();
        Page<Car> page = null;
        if(exp != null){
            page = carRepository.findAll(exp,pageable);
        }else{
            page = carRepository.findAll(pageable);
        }
       return  filterByEnum(page);
    }

    public List<Car> filterByEnum (Page<Car> page) {
        if (enumQuery.equals("")) {
            return page.getContent();
        }
        String[] listEnums = enumQuery.split(";");
        List<Car> carFiltered = new ArrayList<>();
        if (listEnums.length == 1) {
            String value = listEnums[0].split(":")[1];
            if (listEnums[0].contains("fuelType")) {
                for (Car car : page.getContent()) {
                    if (car.getFuelType().toString().equals(value))
                        carFiltered.add(car);
                }
            } else if (listEnums[0].contains("gear")) {
                for (Car car : page.getContent()) {
                    if (car.getGear().toString().equals(value))
                        carFiltered.add(car);
                }
            }
        }
        return carFiltered;
    }

  public  CarPredicatesBuilder initBuilder (String search){
      CarPredicatesBuilder builder = new CarPredicatesBuilder();
      enumQuery = "";
      if (search != null) {
          Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
          Matcher matcher = pattern.matcher(search + ",");
          while (matcher.find()) {
              if(!matcher.group(1).equals("fuelType") && !matcher.group(1).equals("gear") && !matcher.group(1).equals("carState")){
                  builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
              }else{
                  enumQuery +=  matcher.group(1) + matcher.group(2) +  matcher.group(3) + ";" ;
              }
          }
      }
      return builder;
  }

    @RabbitListener(queues = "purchase_service_queue")
    public void listenerMessagesOfQueue(String message) throws JsonProcessingException {
        System.out.println("Message Received from Card... ");
        Boolean isValid = false;
        JsonNode messageReceived = null;
        try {
            messageReceived = objectMapper.readTree(message);
            isValid = true;
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        messageReceived(messageReceived);
    }

    public void messageReceived(JsonNode messageReceived){
        if(messageReceived.get("typeOperation").textValue().equals("VERIFY_IF_EXISTS")) {
            Optional<Car> client = getById(UUID.fromString(messageReceived.get("carId").textValue()));
            if(client.isPresent() && client.get().getCarState() == CarState.AWAITS_DISASSEMBLY){
                sendMessageToCardisassembly(messageReceived.get("itemCarId").textValue(), true);
            }else{
                sendMessageToCardisassembly(messageReceived.get("itemCarId").textValue(), false);
            }
        }
    }
    public void sendMessageToCardisassembly(String itemCarId, Boolean isValid){
        MessageToSend messageToSend = new MessageToSend();
        messageToSend.setItemCarId(itemCarId);
        messageToSend.setSender("PURCHASE-SERVICE");
        if(isValid){
            messageToSend.setTypeResponse("FOUND");
        }else{
            messageToSend.setTypeResponse("NOT_FOUND");
        }
        try {
            System.out.println("Sending message to Cardisassembly service...");
            this.rabbitTemplate.convertAndSend("cardisassembly_service_queue_exchange", "cardisassembly_service_routingKey",ow.writeValueAsString(messageToSend));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
