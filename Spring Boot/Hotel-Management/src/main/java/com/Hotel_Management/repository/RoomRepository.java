package com.Hotel_Management.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Hotel_Management.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

	Page<Room> findByAvailable(boolean available, Pageable pageable);
}
