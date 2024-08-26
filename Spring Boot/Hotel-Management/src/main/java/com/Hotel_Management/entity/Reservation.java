package com.Hotel_Management.entity;

import java.time.LocalDate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.Hotel_Management.dto.ReservationDto;
import com.Hotel_Management.enums.ReservationStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate checkInDate;
	
	private LocalDate checkOutDate;
	
	private Long price;
	
	private ReservationStatus reservationStatus;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "room_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Room room;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	public ReservationDto getReservationDto() {
		ReservationDto reservationDto = new ReservationDto();
		
		reservationDto.setId(id);
		reservationDto.setPrice(price);
		reservationDto.setCheckInDate(checkInDate);
		reservationDto.setCheckOutDate(checkOutDate);
		reservationDto.setReservationStatus(reservationStatus);
		
		reservationDto.setUserId(user.getId());
		reservationDto.setUsername(user.getName());
		
		reservationDto.setRoomId(room.getId());
		reservationDto.setRoomName(room.getName());
		reservationDto.setRoomType(room.getType());
		
		return reservationDto;
	}
}






















