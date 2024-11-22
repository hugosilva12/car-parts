package com.example.cardisassemblyservice.controllers;


import com.example.cardisassemblyservice.domain.Category;
import com.example.cardisassemblyservice.domain.ItemCar;
import com.example.cardisassemblyservice.domain.ItemCarState;
import com.example.cardisassemblyservice.dtos.read.CategoryReadDto;
import com.example.cardisassemblyservice.dtos.read.ItemCarReadDto;
import com.example.cardisassemblyservice.dtos.write.ItemCarWriteDto;
import com.example.cardisassemblyservice.exceptions.BaseException;
import com.example.cardisassemblyservice.mappers.ItemCarImplReadMapper;
import com.example.cardisassemblyservice.mappers.ItemCarWriteMapper;
import com.example.cardisassemblyservice.repository.CategoryRepository;
import com.example.cardisassemblyservice.repository.ItemStateRepository;
import com.example.cardisassemblyservice.services.ItemCarService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.cardisassemblyservice.global.ApiPaths.CarPath.ITEM_CAR;

;

@RestController
@RequestMapping(value = ITEM_CAR)
public class ItemCarController {

    private final ItemCarService itemCarService;


    private final ItemStateRepository itemStateRepository;

    private ObjectWriter ow ;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ItemCarController(ItemCarService itemCarService, ItemStateRepository itemStateRepository,
                             CategoryRepository categoryRepository) {
        this.itemCarService = itemCarService;
        this.itemStateRepository = itemStateRepository;
        this.categoryRepository = categoryRepository;
        this.ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    }

    public String verifyDto(ItemCarWriteDto itemCarWriteDto) {
        if (itemCarWriteDto.getState() == null) {
            return "State cannot be null (percentage)";
        }
        if (itemCarWriteDto.getCategoryId() == null)
            return "Category cannot be null (itemId)";

        if (itemCarWriteDto.getDescription() == null)
             return "Description cannot be null (description)";

        if (itemCarWriteDto.getPrice() == null)
            return "Price cannot be null (price)";


        if (itemCarWriteDto.getItemSku() == null)
            return "Item Sku cannot be null (itemSku)";

        return null;
    }

    @PostMapping
    public ResponseEntity<?> createItemCar(@RequestBody ItemCarWriteDto itemCarWriteDto) {

        String resultVerification = verifyDto(itemCarWriteDto);
        if (resultVerification != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultVerification);
        }
        Optional<ItemCarState> itemState = itemStateRepository.findByPercentage(itemCarWriteDto.getState());

        Optional<Category> category = categoryRepository.findById(itemCarWriteDto.getCategoryId());

        if (!itemState.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("State is invalid");
        }
        if (!category.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("State is invalid");
        }

        try {
            try {
                return ResponseEntity.status(HttpStatus.CREATED).body(ItemCarImplReadMapper.entityToDto(itemCarService.save(ItemCarWriteMapper.dtoToEntity(itemCarWriteDto), itemState.get(), category.get(), ow.writeValueAsString(itemCarWriteDto.getDescription()))));
            } catch (JsonProcessingException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Json of the description is invalid");
            }
        } catch (BaseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<ItemCarReadDto> getItemCarById(@PathVariable("id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(ItemCarImplReadMapper.entityToDto(itemCarService.getById(id).get()));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> updateItemCar(@PathVariable("id") UUID id, @Valid @RequestBody ItemCarWriteDto itemCarWriteDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ItemCarImplReadMapper.entityToDto(itemCarService.updateItemCar(id, ItemCarWriteMapper.dtoToEntity(itemCarWriteDto))));
        } catch (BaseException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> removeItemCar(@PathVariable("id") UUID id) {
        try {
            itemCarService.deleteItemCar(id);
            return ResponseEntity.status(HttpStatus.OK).body("Car has been deleted");
        } catch (BaseException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public Page<ItemCarReadDto> findAllByQuerydsl(@RequestParam(value = "search", required = false) String search, Pageable pageable) {
        List<ItemCar> listToReturn = itemCarService.getCars(search, pageable);
        return new PageImpl<>(Lists.newArrayList(mapperWithFk(listToReturn)),
                pageable, listToReturn.size());
    }


    public List<ItemCarReadDto> mapperWithFk(List<ItemCar> listToReturn) {
        List<ItemCarReadDto> list = new ArrayList<>();
        List<Category> categoryList = categoryRepository.findAll();

        for (ItemCar itemCar : listToReturn) {
            Category categoryToReturn = categoryList.stream().filter(category -> itemCar.getCategory().getId().equals(category.getId())).findFirst().orElse(null);
            ItemCarReadDto id = ItemCarImplReadMapper.entityToDto(itemCar);
            CategoryReadDto categoryReadDto = new CategoryReadDto();
            categoryReadDto.setId(itemCar.getCategory().getId());
            categoryReadDto.setName(categoryToReturn.getName());
            list.add(id);
        }
        return list;
    }
}
