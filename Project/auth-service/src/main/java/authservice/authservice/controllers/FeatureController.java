package authservice.authservice.controllers;

import authservice.authservice.domain.Feature;
import authservice.authservice.dtos.read.FeatureReadDto;
import authservice.authservice.dtos.write.FeatureWriteDto;
import authservice.authservice.exceptions.BaseException;
import authservice.authservice.mapper.FeatureReadMapper;
import authservice.authservice.mapper.FeatureWriteMapper;
import authservice.authservice.services.FeatureService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static authservice.authservice.global.ApiPaths.FeaturePath.FEATURE;


@RestController
@RequestMapping(value = FEATURE)
public class FeatureController {

    private final FeatureService featureService;

    private FeatureWriteMapper mapperWrite = Mappers.getMapper(FeatureWriteMapper.class);

    private FeatureReadMapper mapperRead = Mappers.getMapper(FeatureReadMapper.class);


    @Autowired
    public FeatureController(FeatureService featureService ) {
        this.featureService = featureService;
    }

    @PostMapping
    public ResponseEntity<FeatureReadDto> createFeature(@Valid @RequestBody FeatureWriteDto featureWriteDto) throws BaseException, RoleNotFoundException {
        Feature feature = featureService.save(mapperWrite.dtoToEntity(featureWriteDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapperRead.entityToDto(feature));
    }

    @GetMapping
    public ResponseEntity<List<FeatureReadDto>> getFeatures()  {
        return ResponseEntity.status(HttpStatus.OK).body(mapperRead.entitiesToDtos(featureService.getFeatures()));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<FeatureReadDto> getFeatureById(@PathVariable("id") UUID id) throws BaseException, RoleNotFoundException {
        Optional<Feature> feature = featureService.getFeatureById(id);
        if(feature.isPresent())
          return ResponseEntity.status(HttpStatus.OK).body( mapperRead.entityToDto(feature.get()));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<FeatureReadDto> updateUser(@PathVariable("id") UUID id, @Valid @RequestBody FeatureWriteDto featureWriteDto) throws BaseException, RoleNotFoundException {
        Feature feature = featureService.updateRole(id,mapperWrite.dtoToEntity(featureWriteDto));
        return ResponseEntity.status(HttpStatus.OK).body( mapperRead.entityToDto(feature));
    }
}
