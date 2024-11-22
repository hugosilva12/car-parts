package com.example.storageservice.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "section")
public class Section implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "category_id",nullable = false)
    private Long category;

    @Column(name = "free_positions",nullable = false)
    private Long freePositions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false, foreignKey = @ForeignKey(name = "FK_Item_Category"))
    private Storage storage;

    /*
     * Getters and Setters
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}
