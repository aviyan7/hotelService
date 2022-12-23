package com.hotel.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.hotel.project.model.room.Room;
import com.hotel.project.model.room.Status;

public interface RoomRepository extends JpaRepository<Room, Long>{
List<Room> findByStatus(Status status);
}
