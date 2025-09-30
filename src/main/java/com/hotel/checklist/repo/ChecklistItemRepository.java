package com.hotel.checklist.repo;
import org.springframework.data.jpa.repository.*; import com.hotel.checklist.entity.ChecklistItem;
public interface ChecklistItemRepository extends JpaRepository<ChecklistItem,Long>{}
