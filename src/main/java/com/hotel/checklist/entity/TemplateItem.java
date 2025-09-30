package com.hotel.checklist.entity;
import jakarta.persistence.*; import lombok.*;
@Entity @Table(name="template_items") @Data @NoArgsConstructor @AllArgsConstructor
public class TemplateItem {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @ManyToOne @JoinColumn(name="template_id") private ChecklistTemplate template;
  private String label;
  private boolean required=true;
}
