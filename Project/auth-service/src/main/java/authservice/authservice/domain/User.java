package authservice.authservice.domain;

import com.sun.istack.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "user_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID id;

    @NotBlank(message = "Username can't be blank or null")
    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "user_account_id",  nullable = true, unique = true, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID userAccountId;


    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "is_enabled")
    private boolean isEnabled;

    @ManyToMany(fetch = EAGER)
    @JoinTable(name = "user_features",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "feature_id", referencedColumnName = "feature_id"))
    private final Set<Feature> features = new HashSet<Feature>();


    public void addFeature(Feature feature) {
        features.add(feature);
    }
    /*
     * Getters and Setters y
     */

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public Set<Feature> getFeatures() {
        return features;
    }

    public UUID getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(UUID userAccountId) {
        this.userAccountId = userAccountId;
    }
}
