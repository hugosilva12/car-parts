package com.example.precariousservice.repository;

import com.example.precariousservice.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {


    List<Category> findAll();

}