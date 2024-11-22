package authservice.authservice.services;

import authservice.authservice.domain.Feature;
import authservice.authservice.exceptions.BaseException;
import authservice.authservice.global.Messages;
import authservice.authservice.repository.FeatureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class FeatureService {

    private final FeatureRepository featureRepository;



    @Autowired
    public FeatureService( FeatureRepository featureRepository) {
        this.featureRepository= featureRepository;
    }

    public Feature save(Feature entityToInsert) throws BaseException {
        Optional<Feature> feature = getFeature(entityToInsert.getName());
        if (feature.isPresent())
            throw new BaseException(Messages.FEATURE_EXISTS);
        return featureRepository.save(entityToInsert);
    }

    public Feature updateRole(UUID id, Feature featureToUpdate) throws  BaseException {
        Optional<Feature> feature = getFeatureById(id);
        if (!feature.isPresent()) {
            throw new BaseException(Messages.FEATURE_NOT_FOUND);
        }
        feature.get().setDescription(featureToUpdate.getDescription());
        return  featureRepository.save(feature.get());

    }

    public Optional<Feature> getFeatureById(UUID id) {
        return featureRepository.findById(id);
    }
    public Optional<Feature>  getFeature(String name){
        return featureRepository.findByName(name);
    }

    public List<Feature> getFeatures() {
        return featureRepository.findAll();
    }

}
