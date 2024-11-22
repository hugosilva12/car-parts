package com.example.cardisassemblyservice.dtos.read;

public class CategoryReadDto {

    private Long id;

    private String name;

    private Long quantityStock;

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

    public Long getQuantityStock() {
        return quantityStock;
    }

    public void setQuantityStock(Long quantityStock) {
        this.quantityStock = quantityStock;
    }
}
