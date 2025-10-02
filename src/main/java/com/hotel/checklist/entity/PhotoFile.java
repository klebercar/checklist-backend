package com.hotel.checklist.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "photo_files")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PhotoFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long checklistId;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String contentType;

    @Column(nullable = false)
    private long sizeBytes;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private byte[] data;

    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;
}
