package com.example.cardisassemblyservice.mappers;


import com.example.cardisassemblyservice.domain.ItemCar;
import com.example.cardisassemblyservice.dtos.write.ItemCarWriteDto;
import com.example.cardisassemblyservice.dtos.write.ItemWriteDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


public class ItemCarWriteMapper  {


    public static ItemCarWriteDto entityToDto(ItemCar entity) {
        if (entity == null) {
            return null;
        } else {
            ItemCarWriteDto itemCarWriteDto = new ItemCarWriteDto();
            itemCarWriteDto.setId(entity.getId());
            itemCarWriteDto.setCarId(entity.getCarId());
            itemCarWriteDto.setEmployeeId(entity.getEmployeeId());
            itemCarWriteDto.setItemSku(entity.getItemSku());
            itemCarWriteDto.setPrice(entity.getPrice());
            itemCarWriteDto.setPhotoPath(entity.getPhotoPath());
            return itemCarWriteDto;
        }
    }

    public static ItemCar dtoToEntity(ItemCarWriteDto dto) {
        if (dto == null) {
            return null;
        } else {
            ItemCar itemCar = new ItemCar();
            itemCar.setId(dto.getId());
            itemCar.setCarId(dto.getCarId());
            itemCar.setEmployeeId(dto.getEmployeeId());
            itemCar.setItemSku(dto.getItemSku());
            itemCar.setPrice(dto.getPrice());
            itemCar.setPhotoPath(dto.getPhotoPath());
            return itemCar;
        }
    }

}
