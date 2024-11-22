package com.example.cardisassemblyservice.mappers;

import com.example.cardisassemblyservice.domain.Category;
import com.example.cardisassemblyservice.dtos.read.CategoryReadDto;
import org.mapstruct.Mapper;



@Mapper
public interface CategoryReadMapper extends EntityToDtoMapper<CategoryReadDto, Category> {



}
