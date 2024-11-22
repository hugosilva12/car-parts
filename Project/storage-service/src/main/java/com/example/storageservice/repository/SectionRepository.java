package com.example.storageservice.repository;

import com.example.storageservice.domain.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SectionRepository extends JpaRepository<Section,Long> {

    List<Section> findAll();


    Optional<Section> findByCategory(Long category);
}
