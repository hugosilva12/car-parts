package com.example.precariousservice.services;

import com.example.precariousservice.domain.ItemCarPrice;
import com.example.precariousservice.repository.CategoryRepository;
import com.example.precariousservice.repository.ItemCarPriceRepository;
import com.example.precariousservice.repository.ItemStateRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.xstream.core.BaseException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class ItemCarPriceService {

    private final ItemCarPriceRepository saleRepository;

    private final CategoryRepository categoryRepository;

    private final ItemStateRepository itemStateRepository;

    private final CarSummaryService carSummaryService;

    private ObjectMapper objectMapper ;

    private Random  random;

    @Autowired
    public ItemCarPriceService(ItemCarPriceRepository saleRepository,CategoryRepository categoryRepository,
                               ItemStateRepository itemStateRepository, CarSummaryService carSummaryService){
        this.saleRepository = saleRepository;
        this.categoryRepository = categoryRepository;
        this.itemStateRepository=itemStateRepository;
        this.carSummaryService = carSummaryService;
        this.objectMapper= new ObjectMapper();
        this.random =  new Random();
    }

    public ItemCarPrice save(ItemCarPrice entityToInsert) throws BaseException {
        entityToInsert.setSaleDate(ZonedDateTime.now());
        ItemCarPrice itemCarInserted = saleRepository.save(entityToInsert);
        return itemCarInserted;
    }

    public ItemCarPrice getById(Long uuid) {
        Optional<ItemCarPrice> sale= saleRepository.findById(uuid);
        if(sale.isPresent())
            return sale.get();
        return null;
    }

    public List<ItemCarPrice> findAll() {
        return saleRepository.findAll();
    }

    public List<ItemCarPrice> findAllByCategory(Long uuid) {
        return saleRepository.findByCategory(uuid);
    }


    @RabbitListener(queues = "precarious_service_queue")
    public void listenerMessagesOfQueue(String message)  {
        System.out.println("Message of CarDissambley Service... ");
        Boolean isValid = false;
        JsonNode responseUpdate = null;
        try {
            responseUpdate = objectMapper.readTree(message);
            isValid = true;
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        ItemCarPrice itemCarPrice = new ItemCarPrice();
        itemCarPrice.setPrice(convertStringToBigDecimal(responseUpdate.get("price").toString()));
        itemCarPrice.setDescription(responseUpdate.get("description").textValue());
        itemCarPrice.setCategory(categoryRepository.findById(Long.valueOf(responseUpdate.get("categoryId").toString())).get());
        itemCarPrice.setItemState(itemStateRepository.findById(Long.valueOf(responseUpdate.get("itemStateId").toString())).get());
        int number = random.nextInt(100);
        if(number > 50){
            itemCarPrice.setMarketPrice(itemCarPrice.getPrice().add(itemCarPrice.getPrice().multiply(new BigDecimal(0.25))));
        }else {
            itemCarPrice.setMarketPrice(itemCarPrice.getPrice().subtract(itemCarPrice.getPrice().multiply(new BigDecimal(0.25))));
        }
        save(itemCarPrice);
        carSummaryService.updateCarGeneratedValue(responseUpdate.get("carId").textValue(), convertStringToBigDecimal(responseUpdate.get("price").toString()));

    }

    private BigDecimal convertStringToBigDecimal(String value) {

        BigDecimal valueDecimal = new BigDecimal(value);
        return valueDecimal;
    }
}
