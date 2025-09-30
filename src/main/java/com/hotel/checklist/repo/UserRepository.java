package com.hotel.checklist.repo;
import java.util.*; import org.springframework.data.jpa.repository.*; import com.hotel.checklist.entity.User;
public interface UserRepository extends JpaRepository<User,Long>{ Optional<User> findByEmail(String email); }
