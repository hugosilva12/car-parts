package com.example.cardisassemblyservice.mappers;

import com.example.cardisassemblyservice.domain.ItemCar;
import com.example.cardisassemblyservice.dtos.read.ItemCarReadDto;
import com.example.cardisassemblyservice.dtos.read.ItemStateReadDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ItemCarImplReadMapper {

    public static ItemCarReadDto entityToDto(ItemCar itemCar){
        ObjectMapper objectMapper = new ObjectMapper();
        ItemCarReadDto itemCarReadDto = new ItemCarReadDto();
        itemCarReadDto.setCarId(itemCar.getCarId());
        itemCarReadDto.setItemSku(itemCar.getItemSku());
        itemCarReadDto.setPrice(itemCar.getPrice());
        itemCarReadDto.setPhotoPath(itemCar.getPhotoPath());
        itemCarReadDto.setEmployeeId(itemCar.getEmployeeId());
        itemCarReadDto.setCarId(itemCar.getCarId());
        itemCarReadDto.setId(itemCar.getId());
        itemCarReadDto.setId(itemCar.getId());
        itemCarReadDto.setDate(itemCar.getDate());
        itemCarReadDto.setItemCarLifecycle(itemCar.getItemCarLifecycle());
        try {
            itemCarReadDto.setDescription(objectMapper.readTree(itemCar.getDescription()));
        } catch (JsonProcessingException e) {
            System.out.println("Error: ");
        }
        ItemStateReadDto dto = new ItemStateReadDto();
        dto.setId(itemCar.getItemState().getId());

        if(itemCar.getItemState().getPercentage() != null)
            dto.setPercentage(itemCar.getItemState().getPercentage());

        if(itemCar.getItemState().getDescription() != null)
            dto.setDescription(itemCar.getItemState().getDescription());

        itemCarReadDto.setItemState(dto);
        return itemCarReadDto;
    }
}
