package com.hotel.checklist.service;
import com.hotel.checklist.entity.*; import com.hotel.checklist.repo.*; import lombok.RequiredArgsConstructor; import org.springframework.stereotype.Service; import java.time.OffsetDateTime;
@Service @RequiredArgsConstructor
public class ChecklistService {
  private final ChecklistRepository checklists; private final TemplateRepository templates; private final UserRepository users; private final ChecklistItemRepository items; private final RoomRepository rooms;
  public Checklist create(Long roomId, Long templateId, String email){
    var user=users.findByEmail(email).orElseThrow();
    var room=rooms.findById(roomId).orElseThrow();
    var template=templates.findById(templateId).orElseThrow();
    var c=new Checklist(); c.setRoom(room); c.setTemplate(template); c.setHousekeeper(user);
    c=checklists.save(c);
    for(var ti: template.getItems()){ var it=new ChecklistItem(); it.setChecklist(c); it.setLabel(ti.getLabel()); it.setRequired(ti.isRequired()); items.save(it); }
    return checklists.findById(c.getId()).orElseThrow();
  }
  public Checklist get(Long id){ return checklists.findById(id).orElseThrow(); }
  public ChecklistItem toggle(Long itemId, Boolean checked, String note){ var it=items.findById(itemId).orElseThrow(); it.setChecked(Boolean.TRUE.equals(checked)); it.setNote(note); return items.save(it); }
  public Checklist finish(Long id){ var c=checklists.findById(id).orElseThrow(); c.setStatus("DONE"); c.setFinishedAt(OffsetDateTime.now()); return checklists.save(c); }
}
