package com.example.cardisassemblyservice.dtos.read;

public class ItemReadDto {

    private Long id;


    private String name;

    private String model;

    private Long quantityStock;




    private Integer engine_capacity;


    private CategoryReadDto categoryReadDto;

    /*
     * Getters and Setters
     */


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getQuantityStock() {
        return quantityStock;
    }

    public void setQuantityStock(Long quantityStock) {
        this.quantityStock = quantityStock;
    }

    public Integer getEngine_capacity() {
        return engine_capacity;
    }

    public void setEngine_capacity(Integer engine_capacity) {
        this.engine_capacity = engine_capacity;
    }

    public CategoryReadDto getCategoryReadDto() {
        return categoryReadDto;
    }

    public void setCategoryReadDto(CategoryReadDto categoryReadDto) {
        this.categoryReadDto = categoryReadDto;
    }
}
