package com.example.advertisingservice.mappers;


import com.example.advertisingservice.domain.Advertising;
import com.example.advertisingservice.dtos.write.AdvertisingWriteDto;
import org.mapstruct.Mapper;

@Mapper
public interface AdvertisingWriteMapper extends EntityToDtoMapper<AdvertisingWriteDto, Advertising> {

}
