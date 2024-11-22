package com.example.purchaseservice.mappers;

import com.example.purchaseservice.domain.Car;
import com.example.purchaseservice.dtos.read.CarReadDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;



@Mapper
public interface CarReadMapper extends EntityToDtoMapper<CarReadDto, Car> {

    @Mapping(source = "fuelType", target = "fuelType")
    @Mapping(source = "gear", target = "gear")
    CarReadDto entityToDto(Car entity);


}
