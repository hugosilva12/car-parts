package com.example.cardisassemblyservice.repository;

import com.example.cardisassemblyservice.domain.ItemCarState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemStateRepository extends JpaRepository<ItemCarState, Long> {

   Optional<ItemCarState> findByPercentage(Long percentage);
}
