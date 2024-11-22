package com.example.advertisingservice.controllers;


import com.example.advertisingservice.domain.Advertising;
import com.example.advertisingservice.dtos.read.AdvertisingReadDto;
import com.example.advertisingservice.dtos.write.AdvertisingWriteDto;
import com.example.advertisingservice.exceptions.BaseException;
import com.example.advertisingservice.mappers.AdvertisingReadMapper;
import com.example.advertisingservice.mappers.AdvertisingWriteMapper;
import com.example.advertisingservice.services.AdvertisingService;
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

import static com.example.advertisingservice.global.ApiPaths.CarPath.ADVERTISING;


@RestController
@RequestMapping(value = ADVERTISING)
public class AdvertisingController {

    private final AdvertisingService itemCarService;

    private AdvertisingWriteMapper mapperWrite = Mappers.getMapper(AdvertisingWriteMapper.class);





    @Autowired
    public AdvertisingController(AdvertisingService itemCarService) {
        this.itemCarService = itemCarService;
    }


    @GetMapping(value = "{id}")
    public ResponseEntity<AdvertisingReadDto> getAdvertisingById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(AdvertisingReadMapper.entityToDto(itemCarService.getById(id).get()));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> updateAdvertising(@PathVariable("id") Long id, @Valid @RequestBody AdvertisingWriteDto advertisingWriteDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(AdvertisingReadMapper.entityToDto(itemCarService.updateAdvertising(id, mapperWrite.dtoToEntity(advertisingWriteDto))));
        } catch (BaseException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> removeAdvertising(@PathVariable("id") Long id) {
        try {
            itemCarService.deleteAdvertising(id);
            return ResponseEntity.status(HttpStatus.OK).body("Car has been deleted");
        } catch (BaseException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public Page<AdvertisingReadDto> findAllByQuerydsl(@RequestParam(value = "search", required = false) String search, Pageable pageable) {
        List<Advertising> listToReturn = itemCarService.getAdvertising(search, pageable);
        return new PageImpl<>(Lists.newArrayList(AdvertisingReadMapper.entitiesToDtos(listToReturn)),
                pageable, listToReturn.size());
    }

}
