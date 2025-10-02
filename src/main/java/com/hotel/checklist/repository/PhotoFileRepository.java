package com.hotel.checklist.repository;

import com.hotel.checklist.entity.PhotoFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoFileRepository extends JpaRepository<PhotoFile, Long> {
}

