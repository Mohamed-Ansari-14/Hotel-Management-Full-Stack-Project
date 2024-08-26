package com.Hotel_Management.controller.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Hotel_Management.services.admin.reservation.ReservationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class ReservationController {

	private final ReservationService reservationService;
	
	@GetMapping("/reservations/{pageNumber}")
	public ResponseEntity<?> getAllReservations(@PathVariable int pageNumber){
		try {
			return ResponseEntity.ok(reservationService.getAllReservation(pageNumber));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong.");
		}
	}
	
	@GetMapping("/reservation/{id}/{status}")
	public ResponseEntity<?> changeReservationStatus(@PathVariable Long id, @PathVariable String status){
		boolean success = reservationService.changeReservationStatus(id, status);
		
		if(success) {
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong.");
		}
	}
}











