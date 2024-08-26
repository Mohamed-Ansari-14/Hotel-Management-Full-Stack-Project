package com.Hotel_Management.controller.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Hotel_Management.dto.RoomDto;
import com.Hotel_Management.services.admin.rooms.RoomsService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class RoomsController {

	private final RoomsService roomsService;
	
	@PostMapping("/room")
	public ResponseEntity<?> postRoom(@RequestBody RoomDto roomDto){
		boolean success = roomsService.postRoom(roomDto);
		if(success) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping("/rooms/{pageNumber}")
	public ResponseEntity<?> getAllRooms(@PathVariable int pageNumber){
		return ResponseEntity.ok(roomsService.getAllRooms(pageNumber));
	}
	
	@GetMapping("/room/{id}")
	public ResponseEntity<?> getRoomById(@PathVariable Long id){
		try {
			return ResponseEntity.ok(roomsService.getRoomById(id));
		}catch(EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}catch(Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Somthing went wrong.");
		}
	}
	
	@PutMapping("/room/{id}")
	public ResponseEntity<?> updateRoom(@PathVariable Long id, @RequestBody RoomDto roomDto){
		boolean success = roomsService.updateRoom(id, roomDto);
		if(success) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@DeleteMapping("/room/{id}")
	public ResponseEntity<?> deleteRoom(@PathVariable Long id){
		try {
			roomsService.deleteRoom(id);
			return ResponseEntity.ok(null);
		}catch(EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
















