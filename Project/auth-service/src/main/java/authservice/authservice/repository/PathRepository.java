package authservice.authservice.repository;

import authservice.authservice.domain.Path;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PathRepository extends JpaRepository<Path, Long> {

    List<Path> findAllByPath(String path);

    List<Path> findAllByMethod(String method);

    @Query(value = "SELECT p.path_id, p.path, p.method FROM path AS p INNER JOIN path_features AS pf ON " +
            "p.path_id = pf.path_id INNER JOIN feature AS f ON pf.feature_id = f.feature_id "
            + "WHERE f.name = :feature", nativeQuery = true
    )
    List<Path> findAllByFeature(@Param("feature") String feature);

    @Query(value = "SELECT * FROM path WHERE method = :method AND :path LIKE CONCAT('%', path, '%')", nativeQuery = true)
    Path findOne(@Param("method") String method, @Param("path") String path);

    @Query(value = "SELECT * FROM path WHERE method = :method AND :path LIKE CONCAT('%', path, '%')", nativeQuery = true)
    List<Path> findTwo(@Param("method") String method, @Param("path") String path);
}
