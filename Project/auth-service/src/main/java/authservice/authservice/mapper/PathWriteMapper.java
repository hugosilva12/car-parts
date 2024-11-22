package authservice.authservice.mapper;

import authservice.authservice.domain.Path;
import authservice.authservice.dtos.write.PathWriteDto;
import org.mapstruct.Mapper;
@Mapper
public interface PathWriteMapper extends EntityToDtoMapper<PathWriteDto, Path>{
}
