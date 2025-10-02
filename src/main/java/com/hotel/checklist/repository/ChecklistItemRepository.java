package com.hotel.checklist.repository;
import org.springframework.data.jpa.repository.*; import com.hotel.checklist.entity.ChecklistItem;
public interface ChecklistItemRepository extends JpaRepository<ChecklistItem,Long>{}
