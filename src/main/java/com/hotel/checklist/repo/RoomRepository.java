package com.hotel.checklist.repo;
import org.springframework.data.jpa.repository.*; import com.hotel.checklist.entity.Room;
public interface RoomRepository extends JpaRepository<Room,Long>{}
