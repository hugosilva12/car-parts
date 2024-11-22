package com.example.storageservice.repository;

import com.example.storageservice.domain.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageRepository extends JpaRepository<Storage,Long> {
}
