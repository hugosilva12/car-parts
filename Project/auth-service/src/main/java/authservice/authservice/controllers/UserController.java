package authservice.authservice.controllers;


import authservice.authservice.domain.User;
import authservice.authservice.dtos.read.UserReadDto;
import authservice.authservice.dtos.write.UserFeatureWriteDto;
import authservice.authservice.dtos.write.UserWriteDto;
import authservice.authservice.exceptions.BaseException;
import authservice.authservice.mapper.UserReadMapper;
import authservice.authservice.mapper.UserWriteMapper;
import authservice.authservice.services.UserService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static authservice.authservice.global.ApiPaths.UserPath.*;

@RestController
@RequestMapping(value = USER)
public class UserController {

    private final UserService userService;

    private UserWriteMapper mapperWrite = Mappers.getMapper(UserWriteMapper.class);

    private UserReadMapper mapperRead = Mappers.getMapper(UserReadMapper.class);

    @Autowired
    public UserController(UserService userService ) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserWriteDto userDto)  {
        User user = null;
        try {
            user = userService.save(mapperWrite.dtoToEntity(userDto));
            return ResponseEntity.status(HttpStatus.CREATED).body(mapperRead.entityToDto(user));
        } catch (BaseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @PostMapping(value = ADD_FEATURE)
    public ResponseEntity<UserReadDto> addFeatureToUser(@Valid @RequestBody UserFeatureWriteDto featureWriteDto) throws BaseException {
        if(featureWriteDto.getUserId() == null){
           User user= userService.getUserByUsername(featureWriteDto.getUsername()).get();
            userService.addFeatureToUser(user.getId(),featureWriteDto.name);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        userService.addFeatureToUser(UUID.fromString(featureWriteDto.getUserId()),featureWriteDto.name);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(value = WITH_FEATURE)
    public ResponseEntity<List<User>> getUsersWithFeatures() {
        List<User> rolesResponse = userService.getUsers();
        List<User> listUserReadDto = new ArrayList<>();
        for(User u : rolesResponse){
            u.setPassword(null);
            listUserReadDto.add(u);
        }
        return ResponseEntity.status(HttpStatus.OK).body(listUserReadDto);
    }

    @GetMapping
    public ResponseEntity<List<UserReadDto>> getAllUsers()  {
        return ResponseEntity.status(HttpStatus.OK).body(mapperRead.entitiesToDtos(userService.getUsers()));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<UserReadDto> getUserById(@PathVariable("id") UUID id)  {
        return ResponseEntity.status(HttpStatus.OK).body( mapperRead.entityToDto(userService.getById(id).get()));
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") UUID id, @Valid @RequestBody UserWriteDto userDto) throws BaseException {
        if(userDto.getPassword() == null || userDto.getConfirmPassword() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid password");
        }
        if(!userDto.getPassword().equals(userDto.getConfirmPassword())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Passwords do not match");
        }
        User user = userService.updateUser(id,mapperWrite.dtoToEntity(userDto));
        return ResponseEntity.status(HttpStatus.OK).body( mapperRead.entityToDto(user));
    }

    /**-------------------------------------------------Remove User ------------------------------------**/

    @DeleteMapping(value = "{id}")
    public ResponseEntity<UserReadDto> removeUser(@PathVariable("id") UUID id)  {
        try {
            userService.disableUser(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (BaseException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}


