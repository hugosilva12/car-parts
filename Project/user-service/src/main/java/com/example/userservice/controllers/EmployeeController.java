package com.example.userservice.controllers;

import com.example.userservice.domain.Client;
import com.example.userservice.domain.Employee;
import com.example.userservice.dtos.read.ClientReadDto;
import com.example.userservice.dtos.read.EmployeeReadDto;
import com.example.userservice.dtos.write.EmployeeWriteDto;
import com.example.userservice.exceptions.BaseException;
import com.example.userservice.mappers.EmployeeReadMapper;
import com.example.userservice.mappers.EmployeeWriteMapper;
import com.example.userservice.service.EmployeeService;
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
import java.util.Optional;
import java.util.UUID;

import static com.example.userservice.global.ApiPaths.EmployeePath.EMPLOYEE;
@RestController
@RequestMapping(value = EMPLOYEE)
public class EmployeeController {

    private final EmployeeService employeeService;

    private EmployeeWriteMapper mapperWrite = Mappers.getMapper(EmployeeWriteMapper.class);

    private EmployeeReadMapper mapperRead = Mappers.getMapper(EmployeeReadMapper.class);


    @Autowired
    public EmployeeController(EmployeeService EmployeeService) {
        this.employeeService = EmployeeService;
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeWriteDto employeeWriteDto) throws BaseException {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(mapperRead.entityToDto(employeeService.save(mapperWrite.dtoToEntity(employeeWriteDto),employeeWriteDto.getPassword())));
        } catch (BaseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public Page<EmployeeReadDto> getAllEmployees(Pageable pageable)  {
        List<Employee> listToReturn =  employeeService.getUsers(pageable).getContent();
        return new PageImpl<>(Lists.newArrayList(mapperRead.entitiesToDtos(listToReturn)),
                pageable,listToReturn.size());
    }

    @GetMapping(params = "username")
    public ResponseEntity<?> getEmployeeByUsername(@RequestParam String username) {
        Optional<Employee> employee =  employeeService.getEmployeeByUsername(username);
        if(employee.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(mapperRead.entityToDto(employee.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Username not exists");
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<EmployeeReadDto> getEmployeeById(@PathVariable("id") UUID id)  {
        return ResponseEntity.status(HttpStatus.OK).body( mapperRead.entityToDto(employeeService.getById(id).get()));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<EmployeeReadDto> updateEmployee(@PathVariable("id") UUID id, @Valid @RequestBody EmployeeWriteDto EmployeeWriteDto) throws BaseException {
        Employee Employee = employeeService.updateEmployee(id,mapperWrite.dtoToEntity(EmployeeWriteDto));
        return ResponseEntity.status(HttpStatus.OK).body( mapperRead.entityToDto(Employee));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> removeEmployee(@PathVariable("id") UUID id)  {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.status(HttpStatus.OK).body("User has been deleted");
        } catch (BaseException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
