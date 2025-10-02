package com.hotel.checklist.service;

import com.hotel.checklist.entity.PhotoFile;
import com.hotel.checklist.repository.PhotoFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final PhotoFileRepository repository; // <-- nome padronizado

    @Transactional
    public Long upload(MultipartFile file, Long checklistId) throws IOException {
        PhotoFile pf = new PhotoFile();
        pf.setChecklistId(checklistId);
        pf.setFilename(Optional.ofNullable(file.getOriginalFilename()).orElse("file"));
        pf.setContentType(Optional.ofNullable(file.getContentType()).orElse("application/octet-stream"));
        pf.setSizeBytes(file.getSize());
        pf.setData(file.getBytes());
        pf.setCreatedAt(OffsetDateTime.now());
        repository.save(pf);
        return pf.getId();
    }

    @Transactional
    public String upload(byte[] bytes, String filename, String contentType) {
        PhotoFile pf = new PhotoFile();
        pf.setFilename(filename != null ? filename : ("upload-" + System.currentTimeMillis()));
        pf.setContentType(contentType != null ? contentType : "application/octet-stream");
        pf.setSizeBytes(bytes.length);
        pf.setData(bytes);
        pf.setCreatedAt(OffsetDateTime.now());
        repository.save(pf);
        return String.valueOf(pf.getId());
    }

    @Transactional(readOnly = true)
    public PhotoFile get(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void delete(String key) {
        delete(Long.valueOf(key));
    }
}
