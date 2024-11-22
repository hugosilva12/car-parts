package com.example.cardisassemblyservice.mappers;


import com.example.cardisassemblyservice.domain.Category;
import com.example.cardisassemblyservice.dtos.write.CategoryWriteDto;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryWriteMapper extends EntityToDtoMapper<CategoryWriteDto, Category> {
}
