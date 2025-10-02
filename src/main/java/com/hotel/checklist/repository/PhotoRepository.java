package com.hotel.checklist.repository;
import org.springframework.data.jpa.repository.*; import com.hotel.checklist.entity.Photo;
public interface PhotoRepository extends JpaRepository<Photo,Long>{}
