package authservice.authservice.services;

import authservice.authservice.domain.Feature;
import authservice.authservice.domain.Path;
import authservice.authservice.exceptions.BaseException;
import authservice.authservice.global.Messages;
import authservice.authservice.repository.PathRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class PathService {

    private final PathRepository pathRepository;

    private final FeatureService roleService;

    @Autowired
    public PathService(PathRepository pathRepository, FeatureService roleService) {
        this.pathRepository = pathRepository;
        this.roleService= roleService;
    }

    public Path savePath(Path path) {
        path.setMethod(path.getMethod().toUpperCase());
        if(path.getMethod().equals("POST") || path.getMethod() == "DELETE" || path.getMethod() == "PUT" || path.getMethod().equals("GET")){
            return pathRepository.save(path);
        }
        System.out.println("out ");
        return null;
    }

    public Path verifyIfPathExists(Path pathCreateRequest)   {
        try {
            return  getPath(pathCreateRequest.getMethod().toUpperCase(), pathCreateRequest.getPath());
        } catch (BaseException exception) {
            return null;
        }
    }

    public void updatePath(String method, String path, Path pathUpdateRequest) throws BaseException {
        Path pathToUpdate = getPath(method, path);
        if (!pathToUpdate.getMethod().equals(pathUpdateRequest.getMethod())) {
            pathToUpdate.setMethod(pathUpdateRequest.getMethod());
        }
        if (!pathToUpdate.getPath().equals(pathUpdateRequest.getPath())) {
            pathToUpdate.setPath(pathUpdateRequest.getPath());
        }
        pathRepository.save(pathToUpdate);
    }

    public Path getPath(String method, String path) throws BaseException {

        if(path.contains("/api/users/clients")){
            List<Path> paths = pathRepository.findTwo(method, path);
            for(Path i : paths){
                if(i.getPath().equals("/api/users/clients/"))
                    return i;
            }
        }else if(path.contains("/api/users/employees")){
            List<Path> paths = pathRepository.findTwo(method, path);
            for(Path i : paths){
                if(i.getPath().equals("/api/users/employees/"))
                    return i;
            }

        }else if(path.contains("/api/cardisassembly")){
            if(method.equals("DELETE") || method.equals("PUT")  || method.equals("GET")){
                String[] list = path.toString().split("/");
                String pathAux = list[0] + "/" + list[1] + "/" + list[2] + "/" + list[3];
                List<Path> paths = pathRepository.findTwo(method, pathAux);
                for(Path i : paths){
                    if(i.getPath().equals(pathAux)){
                        return i;
                    }
                }
            }
            List<Path> paths = pathRepository.findTwo(method, path);
            for(Path i : paths){
                if(i.getPath().equals(path)){
                    return i;
                }
            }
        }

        Path pathObj = pathRepository.findOne(method, path);

        if (pathObj == null) {
            throw new BaseException(Messages.PATH_NOT_FOUND);
        }
        return pathObj;
    }

    public List<Path> getPathsByMethod(String method) {
        return pathRepository.findAllByMethod(method);
    }

    public List<Path> getPathsByPath(String path) {
        return pathRepository.findAllByPath(path);
    }

    public List<Path> getPathsByFeature(String role) {
        return pathRepository.findAllByFeature(role);
    }

    public List<Path> getAllPaths() {
        return pathRepository.findAll();
    }

    public void addFeatureToPath(String method, String path, String role) throws BaseException {
        Path pathObj = getPath(method.toUpperCase(), path);
        Optional<Feature> roleObj = roleService.getFeature(role.toUpperCase());
        if (roleObj.isPresent()){
            pathObj.addFeature(roleObj.get());
        }
    }

    public void removeRoleFromPath(String method, String path, String role) throws BaseException {
        Path pathObj = getPath(method.toUpperCase(), path);
        Optional<Feature> roleObj = roleService.getFeature(role.toUpperCase());
        if (roleObj.isPresent()){
            pathObj.removeFeature(roleObj.get());
        }
    }


}
