package com.hotel.checklist.entity;
import jakarta.persistence.*; import lombok.*;
@Entity @Table(name="checklist_items") @Data @NoArgsConstructor @AllArgsConstructor
public class ChecklistItem {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @ManyToOne @JoinColumn(name="checklist_id") private Checklist checklist;
  private String label;
  private boolean required=true;
  private boolean checked=false;
  @Column(columnDefinition="text") private String note;
}
