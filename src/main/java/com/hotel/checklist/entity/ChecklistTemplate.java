package com.hotel.checklist.entity;
import jakarta.persistence.*; import lombok.*; import java.util.*;
@Entity @Table(name="checklist_templates") @Data @NoArgsConstructor @AllArgsConstructor
public class ChecklistTemplate {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  private String name;
  @OneToMany(mappedBy="template", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
  private List<TemplateItem> items=new ArrayList<>();
}
