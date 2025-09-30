package com.hotel.checklist.entity;
import jakarta.persistence.*; import lombok.*; import java.time.OffsetDateTime; import java.util.*;
@Entity @Table(name="checklists") @Data @NoArgsConstructor @AllArgsConstructor
public class Checklist {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @ManyToOne @JoinColumn(name="room_id") private Room room;
  @ManyToOne @JoinColumn(name="template_id") private ChecklistTemplate template;
  @ManyToOne @JoinColumn(name="housekeeper_id") private User housekeeper;
  private String status="OPEN";
  private OffsetDateTime startedAt=OffsetDateTime.now();
  private OffsetDateTime finishedAt;
  @OneToMany(mappedBy="checklist", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
  private List<ChecklistItem> items=new ArrayList<>();
}
