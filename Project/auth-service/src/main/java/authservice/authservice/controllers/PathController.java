package authservice.authservice.controllers;

import authservice.authservice.domain.Path;
import authservice.authservice.dtos.write.AddFeatureToPathWriteDto;
import authservice.authservice.dtos.write.PathWriteDto;
import authservice.authservice.exceptions.BaseException;
import authservice.authservice.global.Messages;
import authservice.authservice.mapper.PathWriteMapper;
import authservice.authservice.services.PathService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static authservice.authservice.global.ApiPaths.Path.PATH;
import static authservice.authservice.global.ApiPaths.Path.REMOVE_FEATURE;
import static authservice.authservice.global.ApiPaths.UserPath.ADD_FEATURE;


@RestController
@RequestMapping(value = PATH)
public class PathController {

    private final PathService pathService;

    private PathWriteMapper pathWriteMapper = Mappers.getMapper(PathWriteMapper.class);

    @Autowired
    public PathController(PathService pathService ) {
        this.pathService = pathService;
    }


    @GetMapping(params = {"method", "path"})
    public ResponseEntity<?> getPath(@RequestParam String method, @RequestParam String path) {
        try {
            return ResponseEntity.ok().body(pathService.getPath(method, path));
        } catch (BaseException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Path not found");
        }
    }

    @GetMapping( params = "method")
    public ResponseEntity<List<Path>> getAllPathsByMethod(@RequestParam String method) {
        return ResponseEntity.ok().body(pathService.getPathsByMethod(method));
    }

    @GetMapping(params = "path")
    public ResponseEntity<List<Path>> getAllPathsByPath(@RequestParam String path) {
        return ResponseEntity.ok().body(pathService.getPathsByPath(path));
    }

    @GetMapping(params = "feature")
    public ResponseEntity<List<Path>> getAllPathsByFeature(@RequestParam String feature) {
        List<Path> pathsByFeature = pathService.getPathsByFeature(feature);
        List<Path> listToReturn = new ArrayList<>();
        for(Path u : pathsByFeature){
            u.setFeatures(null);
            listToReturn.add(u);
        }
        return ResponseEntity.ok().body(listToReturn);
    }

    @GetMapping
    public ResponseEntity<List<Path>> getAllPaths() {
        return ResponseEntity.ok().body(pathService.getAllPaths());
    }

    @PostMapping
    public ResponseEntity<?> createPath(@Valid @RequestBody PathWriteDto pathCreateRequest)  {
        Path pathToInsert = pathService.verifyIfPathExists(pathWriteMapper.dtoToEntity(pathCreateRequest));

        if(pathToInsert != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Messages.PATH_EXISTS);
        }
        Path pathInserted =  pathService.savePath(pathWriteMapper.dtoToEntity(pathCreateRequest));

        if(pathInserted == null){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Path invalid");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(pathInserted);
    }

    @PutMapping
    public ResponseEntity<?> updatePath(@RequestParam String method, @RequestParam String path,
                                        @Valid @RequestBody PathWriteDto pathCreateRequest) throws BaseException {
        pathService.updatePath(method, path, pathWriteMapper.dtoToEntity(pathCreateRequest));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(ADD_FEATURE)
    public ResponseEntity<Void> addFeatureToPath(@RequestBody AddFeatureToPathWriteDto roleToPathRequest) throws BaseException {
        pathService.addFeatureToPath(roleToPathRequest.getMethod(), roleToPathRequest.getPath(), roleToPathRequest.getFeature());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(REMOVE_FEATURE)
    public ResponseEntity<Void> removeFeatureFromPath(@RequestBody AddFeatureToPathWriteDto roleToPathRequest) throws BaseException {
        pathService.removeRoleFromPath(roleToPathRequest.getMethod(), roleToPathRequest.getPath(), roleToPathRequest.getFeature());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
