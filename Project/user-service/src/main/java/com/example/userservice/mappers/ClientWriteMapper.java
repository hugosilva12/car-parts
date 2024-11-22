package com.example.userservice.mappers;


import com.example.userservice.domain.Client;
import com.example.userservice.domain.Employee;
import com.example.userservice.dtos.write.ClientWriteDto;
import com.example.userservice.dtos.write.EmployeeWriteDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ClientWriteMapper extends EntityToDtoMapper<ClientWriteDto, Client> {


}
