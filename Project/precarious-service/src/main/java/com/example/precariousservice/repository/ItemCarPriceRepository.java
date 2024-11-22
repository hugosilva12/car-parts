package com.example.precariousservice.repository;

import com.example.precariousservice.domain.ItemCarPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ItemCarPriceRepository extends JpaRepository<ItemCarPrice,Long> {

    @Query("SELECT i FROM ItemCarPrice i INNER JOIN FETCH i.itemState INNER JOIN FETCH i.category where i.category.id = :id ")
    List<ItemCarPrice> findByCategory(@Param("id") Long id);

    @Query("SELECT i FROM ItemCarPrice i INNER JOIN FETCH i.itemState INNER JOIN FETCH i.category")
    List<ItemCarPrice> findAll();

    @Query("SELECT i FROM ItemCarPrice i INNER JOIN FETCH i.itemState INNER JOIN FETCH i.category where i.id = :id ")
    Optional<ItemCarPrice> findAllByID(@Param("id") Long id);
}