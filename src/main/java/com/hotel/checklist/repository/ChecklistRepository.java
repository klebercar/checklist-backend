package com.hotel.checklist.repository;
import org.springframework.data.jpa.repository.*; import com.hotel.checklist.entity.Checklist;
public interface ChecklistRepository extends JpaRepository<Checklist,Long>{}
