package com.example.precariousservice.controller;

import com.example.precariousservice.domain.ItemCarPrice;
import com.example.precariousservice.dto.ItemCarPriceReadDto;
import com.example.precariousservice.mappers.MapperItemCarDto;
import com.example.precariousservice.services.ItemCarPriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.precariousservice.global.ApiPaths.CarPath.ITEM_CAR_PRICE;

@RestController
@RequestMapping(value = ITEM_CAR_PRICE)
public class ItemCarPriceController {

    private final ItemCarPriceService itemCarPriceService;


    public ItemCarPriceController (ItemCarPriceService itemCarPriceController){
        this.itemCarPriceService = itemCarPriceController;
    }

    @GetMapping(params = "category")
    public ResponseEntity<List<ItemCarPriceReadDto>> getAllItemCarPricesByCategory(@RequestParam Long category) {
        return ResponseEntity.ok().body(MapperItemCarDto.convertEntitiesToDtos(itemCarPriceService.findAllByCategory(category)));
    }

    @GetMapping
    public ResponseEntity<List<ItemCarPriceReadDto>> getAllItemCarPrices() {
        return ResponseEntity.ok().body(MapperItemCarDto.convertEntitiesToDtos(itemCarPriceService.findAll()));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<ItemCarPriceReadDto> getItemCarPricesById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(MapperItemCarDto.convertEntityToDto(itemCarPriceService.getById(id)));
    }
}
