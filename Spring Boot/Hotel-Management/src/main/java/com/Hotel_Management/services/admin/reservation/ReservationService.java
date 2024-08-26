package com.Hotel_Management.services.admin.reservation;

import com.Hotel_Management.dto.ReservationResponseDto;

public interface ReservationService {

	ReservationResponseDto getAllReservation(int pageNumber);
	
	boolean changeReservationStatus(Long id, String status);
}
