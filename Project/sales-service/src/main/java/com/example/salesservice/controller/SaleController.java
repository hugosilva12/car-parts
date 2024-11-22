package com.example.salesservice.controller;


import com.example.salesservice.domain.Sale;
import com.example.salesservice.dtos.read.SaleReadDto;
import com.example.salesservice.dtos.write.SaleWriteDto;
import com.example.salesservice.exceptions.BaseException;
import com.example.salesservice.mappers.SaleReadMapper;
import com.example.salesservice.mappers.SaleWriteMapper;
import com.example.salesservice.services.SalesService;
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
import java.util.UUID;

import static com.example.salesservice.global.ApiPaths.CarPath.SALE;


@RestController
@RequestMapping(value = SALE)
public class SaleController {

    private final SalesService salesService;

    private SaleWriteMapper mapperWrite = Mappers.getMapper(SaleWriteMapper.class);

    private SaleReadMapper mapperRead = Mappers.getMapper(SaleReadMapper.class);

    @Autowired
    public SaleController(SalesService salesService) {
        this.salesService = salesService;
    }


    @PostMapping
    public ResponseEntity<?> createCar(@Valid @RequestBody SaleWriteDto clientWriteDto)  {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(mapperRead.entityToDto(salesService.save(mapperWrite.dtoToEntity(clientWriteDto), clientWriteDto.getItemCarId())));
        } catch (BaseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<SaleReadDto> getCarById(@PathVariable("id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(mapperRead.entityToDto(salesService.getById(id).get()));
    }

   /* @PutMapping(value = "{id}")
    public ResponseEntity<?> updateCar(@PathVariable("id") UUID id, @Valid @RequestBody SaleWriteDto CarWriteDto)  {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mapperRead.entityToDto(salesService.u(id, mapperWrite.dtoToEntity(CarWriteDto))));
        } catch (BaseException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }*/

    @GetMapping
    public Page<SaleReadDto> findAllByQuerydsl(@RequestParam(value = "search", required = false) String search, Pageable pageable) {
        List<Sale> listToReturn =  salesService.getSale(search,pageable);
        return new PageImpl<>(Lists.newArrayList(mapperRead.entitiesToDtos(listToReturn)),
                pageable,listToReturn.size());
    }

}
