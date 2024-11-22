package com.example.purchaseservice.mappers;


import com.example.purchaseservice.domain.Car;
import com.example.purchaseservice.dtos.write.CarWriteDto;
import org.mapstruct.Mapper;

@Mapper
public interface CarWriteMapper extends EntityToDtoMapper<CarWriteDto, Car> {
}
