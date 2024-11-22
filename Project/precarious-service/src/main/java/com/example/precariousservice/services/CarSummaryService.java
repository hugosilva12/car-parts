package com.example.precariousservice.services;

import com.example.precariousservice.domain.CarSummary;
import com.example.precariousservice.repository.CarSummaryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional
public class CarSummaryService {

    private final CarSummaryRepository carSummaryRepository;


    public CarSummaryService( CarSummaryRepository carSummaryRepository){
        this.carSummaryRepository = carSummaryRepository;
    }

    public CarSummary getById(String uuid) {
        Optional<CarSummary> sale= carSummaryRepository.findByCarId(uuid);
        if(sale.isPresent())
            return sale.get();
        return null;
    }

    public CarSummary updateCarGeneratedValue(String uuid, BigDecimal value){
        CarSummary carSummary = getById(uuid);
        if(carSummary != null){
            carSummary.setValueGenerated(carSummary.getValueGenerated().add(value));
            return carSummary;
        }
        CarSummary carSummaryToInsert = new CarSummary();
        carSummaryToInsert.setCarId(uuid);
        carSummaryToInsert.setValueGenerated(value);
      return   carSummaryRepository.save(carSummaryToInsert);
    }
}
