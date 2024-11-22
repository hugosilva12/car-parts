package com.example.advertisingservice.mappers;

import com.example.advertisingservice.domain.Advertising;
import com.example.advertisingservice.dtos.read.AdvertisingReadDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;


public class AdvertisingReadMapper {

    public  static AdvertisingReadDto entityToDto(Advertising entity) {
        if (entity == null) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        AdvertisingReadDto advertisingReadDto = new AdvertisingReadDto();

        advertisingReadDto.setId(entity.getId());
        advertisingReadDto.setItemCar(entity.getItemCar());
        advertisingReadDto.setPrice(entity.getPrice());
        advertisingReadDto.setItemState(entity.getItemState());
        advertisingReadDto.setPhotoPath(entity.getPhotoPath());
        advertisingReadDto.setCategory(entity.getCategory());
        try {
            advertisingReadDto.setDescription(objectMapper.readTree(entity.getDescription()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return advertisingReadDto;
    }
    public  static List<AdvertisingReadDto> entitiesToDtos(List<Advertising> entities) {
        if ( entities == null ) {
            return null;
        }
        List<AdvertisingReadDto> list = new ArrayList<AdvertisingReadDto>( entities.size() );

        for ( Advertising advertising : entities ) {
            list.add( entityToDto( advertising ) );
        }
        return list;
    }
}
