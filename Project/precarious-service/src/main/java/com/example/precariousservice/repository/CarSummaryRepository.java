package com.example.precariousservice.repository;

import com.example.precariousservice.domain.CarSummary;
import com.example.precariousservice.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarSummaryRepository  extends JpaRepository<CarSummary, Long> {

    Optional<CarSummary> findByCarId(String ca);
}
