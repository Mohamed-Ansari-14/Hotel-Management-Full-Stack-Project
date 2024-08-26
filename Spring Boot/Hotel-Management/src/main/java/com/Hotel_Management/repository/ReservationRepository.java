package com.Hotel_Management.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Hotel_Management.entity.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{

	Page<Reservation> findAllByUserId(Pageable pageable, Long userId);
}
