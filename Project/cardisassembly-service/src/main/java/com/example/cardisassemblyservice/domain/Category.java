package com.example.cardisassemblyservice.domain;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "category")
public class Category  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", updatable = false, unique = true, nullable = false)
    private String name;

    @Column(name = "quantity_stock", nullable = false)
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
