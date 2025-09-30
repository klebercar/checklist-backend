package com.hotel.checklist.controller;
import com.hotel.checklist.entity.*; import com.hotel.checklist.repo.*; import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/templates") @RequiredArgsConstructor @PreAuthorize("hasRole('ADMIN')")
public class TemplateController {
  private final TemplateRepository templates; private final TemplateItemRepository items;
  @PostMapping public ChecklistTemplate create(@RequestBody ChecklistTemplate t){ if(t.getItems()!=null) t.getItems().forEach(i->i.setTemplate(t)); return templates.save(t); }
  @GetMapping public List<ChecklistTemplate> list(){ return templates.findAll(); }
}
