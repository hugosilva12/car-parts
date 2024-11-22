package com.example.storageservice.controller;

import com.example.storageservice.dtos.SectionReadDto;
import com.example.storageservice.mappers.SectionReadMapper;
import com.example.storageservice.service.SectionService;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.storageservice.global.ApiPaths.CarPath.SECTIONS;

@RestController
@RequestMapping(value = SECTIONS)
public class SectionController {

    private final SectionService sectionService;

    private SectionReadMapper mapperRead = Mappers.getMapper(SectionReadMapper.class);

    public SectionController (SectionService itemCarPriceController){
        this.sectionService = itemCarPriceController;
    }
    @GetMapping
    public ResponseEntity<List<SectionReadDto>> getCarSummaryById() {
        return ResponseEntity.ok().body(mapperRead.entitiesToDtos(sectionService.findAll()));
    }

}
