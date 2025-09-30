package com.hotel.checklist.entity;
import jakarta.persistence.*; import lombok.*; import java.time.OffsetDateTime;
@Entity @Table(name="photos") @Data @NoArgsConstructor @AllArgsConstructor
public class Photo {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @ManyToOne @JoinColumn(name="checklist_id") private Checklist checklist;
  private String s3Key;
  @Column(columnDefinition="text") private String url;
  private OffsetDateTime createdAt=OffsetDateTime.now();
}
