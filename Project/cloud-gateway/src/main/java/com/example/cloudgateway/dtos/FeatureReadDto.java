package com.example.cloudgateway.dtos;

import java.util.UUID;

public class FeatureReadDto {

    public UUID featureId;

    public String name;

    public String description;

    /*
     * Getters and Setters y
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getFeatureId() {
        return featureId;
    }

    public void setFeatureId(UUID featureId) {
        this.featureId = featureId;
    }
}
