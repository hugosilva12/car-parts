package com.example.cardisassemblyservice.controllers;


import com.example.cardisassemblyservice.dtos.read.CategoryReadDto;
import com.example.cardisassemblyservice.dtos.write.CategoryWriteDto;
import com.example.cardisassemblyservice.dtos.write.ItemCarWriteDto;
import com.example.cardisassemblyservice.exceptions.BaseException;
import com.example.cardisassemblyservice.mappers.CategoryReadMapper;
import com.example.cardisassemblyservice.mappers.CategoryWriteMapper;
import com.example.cardisassemblyservice.services.CategoryService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.cardisassemblyservice.global.ApiPaths.ItemPath.CATEGORY;



@RestController
@RequestMapping(value = CATEGORY)
public class CategoryController {


    private final CategoryService categoryService;

    private CategoryWriteMapper mapperWrite = Mappers.getMapper(CategoryWriteMapper.class);

    private CategoryReadMapper mapperRead = Mappers.getMapper(CategoryReadMapper.class);

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public String verifyDto(ItemCarWriteDto itemCarWriteDto) {
        if (itemCarWriteDto.getState() == null) {
            return "State cannot be null (percentage)";
        }
        if (itemCarWriteDto.getCategoryId() == null)
            return "Item cannot be null (itemId)";

        if (itemCarWriteDto.getDescription() == null)
             return "Description cannot be null (description)";

        if (itemCarWriteDto.getPrice() == null)
            return "Price cannot be null (price)";


        if (itemCarWriteDto.getItemSku() == null)
            return "Item Sku cannot be null (itemSku)";

        return null;
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryWriteDto categoryWriteDto) {

        try {
                return ResponseEntity.status(HttpStatus.CREATED).body(mapperRead.entityToDto(categoryService.save(mapperWrite.dtoToEntity(categoryWriteDto))));
        } catch (BaseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<CategoryReadDto> getItemCarById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(mapperRead.entityToDto(categoryService.findById(id).get()));
    }


    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> removeCategory(@PathVariable("id") Long id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.status(HttpStatus.OK).body("Car has been deleted");
        } catch (BaseException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public List<CategoryReadDto> findAll(@RequestParam(value = "search", required = false) String search, Pageable pageable) {
      return mapperRead.entitiesToDtos(categoryService.findAll());

    }

}
