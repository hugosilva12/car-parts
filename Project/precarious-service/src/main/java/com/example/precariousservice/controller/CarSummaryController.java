package com.example.precariousservice.controller;

import com.example.precariousservice.dto.CarSummaryReadDto;
import com.example.precariousservice.dto.ItemCarPriceReadDto;
import com.example.precariousservice.mappers.MapperItemCarDto;
import com.example.precariousservice.services.CarSummaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.precariousservice.global.ApiPaths.CarPath.ITEM_CAR_PRICE;

@RestController
@RequestMapping(value = ITEM_CAR_PRICE + "/car")
public class CarSummaryController {

    private final CarSummaryService carSummaryService;


    public CarSummaryController (CarSummaryService itemCarPriceController){
        this.carSummaryService = itemCarPriceController;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<CarSummaryReadDto> getCarSummaryById(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(MapperItemCarDto.convertCarSummary(carSummaryService.getById(id)));
    }

}
