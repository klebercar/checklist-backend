package com.hotel.checklist.entity;
import jakarta.persistence.*; import lombok.*;
@Entity @Table(name="rooms") @Data @NoArgsConstructor @AllArgsConstructor
public class Room {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @Column(unique=true) private String code;
  private String description;
  private Integer floor;
}
