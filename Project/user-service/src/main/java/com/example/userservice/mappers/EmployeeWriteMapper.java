package com.example.userservice.mappers;


import com.example.userservice.domain.Employee;
import com.example.userservice.dtos.write.EmployeeWriteDto;
import org.mapstruct.Mapper;

@Mapper
public interface EmployeeWriteMapper extends EntityToDtoMapper<EmployeeWriteDto, Employee> {


}
