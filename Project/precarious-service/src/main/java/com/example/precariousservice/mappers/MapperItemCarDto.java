package com.example.precariousservice.mappers;

import com.example.precariousservice.domain.CarSummary;
import com.example.precariousservice.domain.ItemCarPrice;
import com.example.precariousservice.dto.CarSummaryReadDto;
import com.example.precariousservice.dto.CategoryReadDto;
import com.example.precariousservice.dto.ItemCarPriceReadDto;
import com.example.precariousservice.dto.ItemStateReadDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class MapperItemCarDto {

    public static ItemCarPriceReadDto convertEntityToDto(ItemCarPrice itemCarPrice){
        ObjectMapper objectMapper = new ObjectMapper();
        ItemCarPriceReadDto itemCarPriceReadDto = new ItemCarPriceReadDto();
        CategoryReadDto categoryReadDto = new CategoryReadDto();
        categoryReadDto.setId(itemCarPrice.getCategory().getId());
        categoryReadDto.setName(itemCarPrice.getCategory().getName());
        itemCarPriceReadDto.setCategory(categoryReadDto);

        itemCarPriceReadDto.setMarketPrice(itemCarPrice.getMarketPrice());
        itemCarPriceReadDto.setPrice(itemCarPrice.getPrice());
        itemCarPriceReadDto.setId(itemCarPrice.getId());
        itemCarPriceReadDto.setSaleDate(itemCarPrice.getSaleDate());
       try {
            itemCarPriceReadDto.setDescription(objectMapper.readTree(itemCarPrice.getDescription()));
        } catch (JsonProcessingException e) {
            System.out.println("Error: ");
        }

        ItemStateReadDto dto = new ItemStateReadDto();
        dto.setId(itemCarPrice.getItemState().getId());

        if(itemCarPrice.getItemState().getPercentage() != null)
            dto.setPercentage(itemCarPrice.getItemState().getPercentage());

        if(itemCarPrice.getItemState().getDescription() != null)
            dto.setDescription(itemCarPrice.getItemState().getDescription());

        itemCarPriceReadDto.setItemState(dto);
        return itemCarPriceReadDto;
    }
    public static List<ItemCarPriceReadDto> convertEntitiesToDtos(List<ItemCarPrice> itemCarPrices){
        List<ItemCarPriceReadDto> list = new ArrayList<>();
        for(ItemCarPrice itemCarPrice : itemCarPrices){
            list.add(convertEntityToDto(itemCarPrice));
        }
        return list;
    }

    public static CarSummaryReadDto convertCarSummary(CarSummary carSummary){
        CarSummaryReadDto carSummaryReadDto = new CarSummaryReadDto();
        carSummaryReadDto.setCarId(carSummary.getCarId());
        carSummaryReadDto.setValueGenerated(carSummary.getValueGenerated());
        return carSummaryReadDto;
    }
}
