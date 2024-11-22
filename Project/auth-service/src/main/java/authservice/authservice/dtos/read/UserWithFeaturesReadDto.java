package authservice.authservice.dtos.read;

import authservice.authservice.domain.Feature;
import authservice.authservice.dtos.global.BasicDto;

import java.util.Set;

public class UserWithFeaturesReadDto extends BasicDto {


    private String username;

    private boolean isEnabled;

    private Set<Feature> features;

    public UserWithFeaturesReadDto(String username, boolean isEnabled, Set<Feature> features) {
        this.username = username;
        this.isEnabled = isEnabled;
        this.features = features;
    }


    /*
     * Getters and Setters y
     */

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void setFeatures(Set<Feature> features) {
        this.features = features;
    }
}
