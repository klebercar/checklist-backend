package com.hotel.checklist.controller;
import com.hotel.checklist.entity.Room; import com.hotel.checklist.repository.RoomRepository; import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/rooms") @RequiredArgsConstructor @PreAuthorize("hasRole('ADMIN')")
public class RoomController { private final RoomRepository rooms;
  @GetMapping public List<Room> list(){ return rooms.findAll(); }
  @PostMapping public Room create(@RequestBody Room r){ return rooms.save(r); }
}
