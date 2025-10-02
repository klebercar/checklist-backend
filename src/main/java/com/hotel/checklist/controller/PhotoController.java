package com.hotel.checklist.controller;

import com.hotel.checklist.entity.PhotoFile;
import com.hotel.checklist.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/photos")
@RequiredArgsConstructor
public class PhotoController {

    private final StorageService storage;


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Long> upload(@RequestParam("file") MultipartFile file,
                                       @RequestParam(value = "checklistId", required = false) Long checklistId) throws Exception {
        Long id = storage.upload(file, checklistId);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable Long id) {
        PhotoFile pf = storage.get(id);
        ByteArrayResource body = new ByteArrayResource(pf.getData());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(pf.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + pf.getFilename() + "\"")
                .contentLength(pf.getSizeBytes())
                .body(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        storage.delete(String.valueOf(id));
        return ResponseEntity.noContent().build();
    }
}
