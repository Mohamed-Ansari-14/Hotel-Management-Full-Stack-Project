package com.Hotel_Management.services.customer.booking;

import com.Hotel_Management.dto.ReservationDto;
import com.Hotel_Management.dto.ReservationResponseDto;

public interface BookingService {

	boolean postReservation(ReservationDto reservationDto);
	
	ReservationResponseDto getAllReservationByUserId(Long userId, int pageNumber);
}
