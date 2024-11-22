package com.example.userservice.mappers;

import org.mapstruct.MappingTarget;

import java.util.List;

public interface EntityToDtoMapper<T, S> {
  T entityToDto(S entity);

  S dtoToEntity(T dto);

  void updateEntityFromDto(T dto, @MappingTarget S entity);

  List<T> entitiesToDtos(List<S> entities);

  Iterable<T> entitiesToDtos(Iterable<S> entities);

  List<S> dtosToEntities(List<T> dtos);
}
