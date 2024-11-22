package com.example.userservice.mappers;

import com.example.userservice.domain.Employee;
import com.example.userservice.dtos.read.EmployeeReadDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface EmployeeReadMapper extends EntityToDtoMapper<EmployeeReadDto, Employee> {

    @Mapping(source = "userState", target = "userState")
    EmployeeReadDto entityToDto(Employee entity);
}


