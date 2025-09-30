package com.hotel.checklist.repo;
import org.springframework.data.jpa.repository.*; import com.hotel.checklist.entity.TemplateItem;
public interface TemplateItemRepository extends JpaRepository<TemplateItem,Long>{}
