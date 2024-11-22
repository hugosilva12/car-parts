package com.example.cloudgateway.dtos;


import java.util.Set;


public class UserWithFeaturesReadDto extends BasicDto  {
    private String username;

    private boolean isEnabled;

    private Set<FeatureReadDto> features;

    public UserWithFeaturesReadDto(String username, boolean isEnabled, Set<FeatureReadDto> features) {
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

    public Set<FeatureReadDto> getFeatures() {
        return features;
    }

    public void setFeatures(Set<FeatureReadDto> features) {
        this.features = features;
    }
}
