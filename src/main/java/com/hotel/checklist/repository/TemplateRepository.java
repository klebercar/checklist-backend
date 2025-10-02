package com.hotel.checklist.repository;
import org.springframework.data.jpa.repository.*; import com.hotel.checklist.entity.ChecklistTemplate;
public interface TemplateRepository extends JpaRepository<ChecklistTemplate,Long>{}
