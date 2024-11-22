package com.example.purchaseservice.controllers;

import com.example.purchaseservice.domain.Car;
import com.example.purchaseservice.dtos.read.CarReadDto;
import com.example.purchaseservice.dtos.write.CarWriteDto;
import com.example.purchaseservice.exceptions.BaseException;
import com.example.purchaseservice.mappers.CarReadMapper;
import com.example.purchaseservice.mappers.CarWriteMapper;
import com.example.purchaseservice.services.CarService;
import com.google.common.collect.Lists;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static com.example.purchaseservice.global.ApiPaths.CarPath.CAR;

@RestController
@RequestMapping(value = CAR)
public class CarController {

    private final CarService carService;

    private CarWriteMapper mapperWrite = Mappers.getMapper(CarWriteMapper.class);

    private CarReadMapper mapperRead = Mappers.getMapper(CarReadMapper.class);

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }


    @PostMapping
    public ResponseEntity<?> createCar(@Valid @RequestBody CarWriteDto carWriteDto)  {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(mapperRead.entityToDto(carService.save(mapperWrite.dtoToEntity(carWriteDto))));
        } catch (BaseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<CarReadDto> getCarById(@PathVariable("id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(mapperRead.entityToDto(carService.getById(id).get()));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> updateCar(@PathVariable("id") UUID id, @Valid @RequestBody CarWriteDto carWriteDto)  {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mapperRead.entityToDto(carService.updateCar(id, mapperWrite.dtoToEntity(carWriteDto),carWriteDto.getCarState())));
        } catch (BaseException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> removeCar(@PathVariable("id") UUID id) {
        try {
            carService.deleteCar(id);
            return ResponseEntity.status(HttpStatus.OK).body("Car has been deleted");
        } catch (BaseException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public Page<CarReadDto> findAllByQuerydsl(@RequestParam(value = "search", required = false) String search, Pageable pageable) {
        List<Car> listToReturn =  carService.getCars(search,pageable);
        return new PageImpl<>(Lists.newArrayList(mapperRead.entitiesToDtos(listToReturn)),
                pageable,listToReturn.size());
    }

}
