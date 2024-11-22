package com.example.storageservice.mappers;


import com.example.storageservice.domain.Section;
import com.example.storageservice.dtos.SectionReadDto;
import org.mapstruct.Mapper;


@Mapper
public interface SectionReadMapper extends EntityToDtoMapper<SectionReadDto, Section> {

}
