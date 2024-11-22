package authservice.authservice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.FetchType.EAGER;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "path")
public class Path implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "path_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID id;


    @Size(max = 10)
    @Column(name = "method")
    private String method;


    @Size(max = 255)
    @Column(name = "path")
    private String path;

    @ManyToMany(fetch = EAGER)
    @JoinTable(name = "path_features",
            joinColumns = @JoinColumn(name = "path_id", referencedColumnName = "path_id"),
            inverseJoinColumns = @JoinColumn(name = "feature_id", referencedColumnName = "feature_id"))
    private List<Feature> features;

    public Path(String method, String path) {
        this.method = method;
        this.path = path;
        this.features = new ArrayList<>();
    }

    public Path(String method, String path, List<Feature> roles) {
        this.method = method;
        this.path = path;
        this.features = roles;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void addFeature(Feature role) {
        features.add(role);
    }

    public void removeFeature(Feature role) {
        features.remove(role);
    }

    public boolean containsFeature(String roleName) {
        for (Feature feature : this.features) {
            if (feature.getName().equals(roleName)) {
                return true;
            }
        }
        return false;
    }

}
