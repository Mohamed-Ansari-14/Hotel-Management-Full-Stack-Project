package com.Hotel_Management.controller.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Hotel_Management.services.customer.room.RoomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class RoomController {

	private final RoomService roomService;
	
	@GetMapping("/rooms/{pageNumber}")
	public ResponseEntity<?> getAvailableRooms(@PathVariable int pageNumber){
		return ResponseEntity.ok(roomService.getAvailableRooms(pageNumber));
	}
}
