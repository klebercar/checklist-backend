package com.hotel.checklist.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;


import java.time.OffsetDateTime;

@Entity
@Table(name = "photo_files")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PhotoFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(name = "checklist_id", nullable = false)
    private Long checklistId;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String contentType;

    @Column(nullable = false)
    private long sizeBytes;

    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    // MAPEAR EXPLICITAMENTE COMO BYTEA
    @JdbcTypeCode(SqlTypes.VARBINARY)
    @Column(name = "data", nullable = false, columnDefinition = "bytea")
    private byte[] data;



}
