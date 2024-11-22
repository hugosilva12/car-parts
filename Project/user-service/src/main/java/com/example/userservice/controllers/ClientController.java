package com.example.userservice.controllers;

import com.example.userservice.domain.Client;
import com.example.userservice.dtos.read.ClientReadDto;
import com.example.userservice.dtos.write.ClientWriteDto;
import com.example.userservice.exceptions.BaseException;
import com.example.userservice.exceptions.Messages;
import com.example.userservice.mappers.ClientReadMapper;
import com.example.userservice.mappers.ClientWriteMapper;
import com.example.userservice.service.ClientService;
import com.google.common.collect.Lists;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.userservice.global.ApiPaths.ClientPath.CLIENT;


@RestController
@RequestMapping(value = CLIENT)
public class ClientController {


    private final ClientService clientService;

    private ClientWriteMapper mapperWrite = Mappers.getMapper(ClientWriteMapper.class);

    private ClientReadMapper mapperRead = Mappers.getMapper(ClientReadMapper.class);


    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<?> createClient(@Valid @RequestBody ClientWriteDto clientWriteDto)  {
        Client clientToInsert = mapperWrite.dtoToEntity(clientWriteDto);

        if (clientWriteDto.getTypeClient() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Messages.TYPE_USER);

        clientToInsert.setTypeClient(clientWriteDto.getTypeClient());
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(mapperRead.entityToDto(clientService.save(clientToInsert,clientWriteDto.getPassword())));
        } catch (BaseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public Page<ClientReadDto> getAllClients(Pageable pageable) {
        List<Client> listToReturn =  clientService.getClients(pageable).getContent();
        return new PageImpl<>(Lists.newArrayList(mapperRead.entitiesToDtos(listToReturn)),
                pageable,listToReturn.size());
    }
    @GetMapping(params = "username")
    public ResponseEntity<?> getClientByUsername(@RequestParam String username) {
        Optional<Client> client =  clientService.getClientByUsername(username);
        if(client.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(mapperRead.entityToDto(client.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Username not exists");
    }


    @GetMapping(value = "{id}")
    public ResponseEntity<ClientReadDto> getClientById(@PathVariable("id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(mapperRead.entityToDto(clientService.getById(id).get()));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> updateClient(@PathVariable("id") UUID id, @Valid @RequestBody ClientWriteDto clientWriteDto)  {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mapperRead.entityToDto(clientService.updateClient(id, mapperWrite.dtoToEntity(clientWriteDto))));
        } catch (BaseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> removeClient(@PathVariable("id") UUID id) {
        try {
            clientService.deleteClient(id);
            return ResponseEntity.status(HttpStatus.OK).body("User has been deleted");
        } catch (BaseException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
