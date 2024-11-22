package com.example.storageservice.dtos;



public class SectionReadDto {

    private Long category;

    private Long freePositions;

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public Long getFreePositions() {
        return freePositions;
    }

    public void setFreePositions(Long freePositions) {
        this.freePositions = freePositions;
    }
}
