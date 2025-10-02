package com.hotel.checklist.controller;
import com.hotel.checklist.dto.*; import com.hotel.checklist.entity.*; import com.hotel.checklist.repository.*; import com.hotel.checklist.service.*; import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize; import org.springframework.security.core.Authentication; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/checklists") @RequiredArgsConstructor
public class ChecklistController {
  private final ChecklistService service; private final ChecklistItemRepository items; private final ChecklistRepository checklists;
  @PostMapping @PreAuthorize("hasAnyRole('ADMIN','HOUSEKEEPER')") public Checklist create(@RequestBody CreateChecklistReq req, Authentication auth){ String email=(String)auth.getPrincipal(); return service.create(req.roomId(), req.templateId(), email); }
  @GetMapping("/{id}") public Checklist get(@PathVariable Long id){ return service.get(id); }
  @PostMapping("/{id}/items/{itemId}/toggle") public ChecklistItem toggle(@PathVariable Long id,@PathVariable Long itemId,@RequestBody ToggleReq req){ return service.toggle(itemId, req.checked(), req.note()); }
  @PostMapping("/{id}/finish") public Checklist finish(@PathVariable Long id){ return service.finish(id); }
}
