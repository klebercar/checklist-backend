package com.hotel.checklist.controller;
import com.hotel.checklist.entity.*; import com.hotel.checklist.repo.*; import com.hotel.checklist.service.*; import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*; import org.springframework.web.multipart.MultipartFile;
@RestController @RequestMapping("/api/photos") @RequiredArgsConstructor
public class PhotoController {
  private final StorageService storage; private final PhotoRepository photos; private final ChecklistRepository checklists;
  @PostMapping("/upload") public Photo upload(@RequestParam Long checklistId, @RequestParam("file") MultipartFile file) throws Exception {
    var c=checklists.findById(checklistId).orElseThrow();
    var up=storage.store(file.getBytes(), file.getOriginalFilename());
    Photo p=new Photo(null, c, up.key(), up.url(), null);
    return photos.save(p);
  }
}
