package com.example.precariousservice.dto;

public class ItemStateReadDto {

    private Long id;


    private Long percentage;


    private String description;

    /*
     * Getters and Setters
     */


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPercentage() {
        return percentage;
    }

    public void setPercentage(Long percentage) {
        this.percentage = percentage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
