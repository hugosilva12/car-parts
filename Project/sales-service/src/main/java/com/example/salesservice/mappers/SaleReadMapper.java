package com.example.salesservice.mappers;

import com.example.salesservice.domain.Sale;
import com.example.salesservice.dtos.read.SaleReadDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;



@Mapper
public interface SaleReadMapper extends EntityToDtoMapper<SaleReadDto, Sale> {

    @Mapping(source = "state", target = "state")
    @Mapping(source = "itemCar", target = "itemCar")
    SaleReadDto entityToDto(Sale entity);


}
