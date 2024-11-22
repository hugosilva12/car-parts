package authservice.authservice.repository;

import authservice.authservice.domain.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FeatureRepository extends JpaRepository<Feature, UUID> {

    Optional<Feature> findByName(String name);
}
