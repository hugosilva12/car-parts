package com.example.userservice.mappers;

import com.example.userservice.domain.Client;
import com.example.userservice.domain.Employee;
import com.example.userservice.dtos.read.ClientReadDto;
import com.example.userservice.dtos.write.EmployeeWriteDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ClientReadMapper extends EntityToDtoMapper<ClientReadDto, Client> {
    @Mapping(source = "userState", target = "userState")
    ClientReadDto entityToDto(Client entity);
}
