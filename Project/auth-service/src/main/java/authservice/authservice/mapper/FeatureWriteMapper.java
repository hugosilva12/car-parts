package authservice.authservice.mapper;

import authservice.authservice.domain.Feature;
import authservice.authservice.dtos.write.FeatureWriteDto;
import org.mapstruct.Mapper;

@Mapper
public interface FeatureWriteMapper extends EntityToDtoMapper<FeatureWriteDto, Feature>{
}
