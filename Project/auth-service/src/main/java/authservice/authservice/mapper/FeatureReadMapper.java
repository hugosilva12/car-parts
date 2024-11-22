package authservice.authservice.mapper;

import authservice.authservice.domain.Feature;
import authservice.authservice.dtos.read.FeatureReadDto;
import org.mapstruct.Mapper;

@Mapper
public interface FeatureReadMapper extends EntityToDtoMapper<FeatureReadDto, Feature>{
}
