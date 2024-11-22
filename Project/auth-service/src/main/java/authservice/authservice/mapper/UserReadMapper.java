package authservice.authservice.mapper;

import authservice.authservice.domain.User;
import authservice.authservice.dtos.read.UserReadDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserReadMapper extends EntityToDtoMapper<UserReadDto, User>{
}
