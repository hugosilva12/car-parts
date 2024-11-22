package authservice.authservice.mapper;


import authservice.authservice.domain.User;
import authservice.authservice.dtos.write.UserWriteDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserWriteMapper extends EntityToDtoMapper<UserWriteDto, User> {
}
