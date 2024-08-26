package com.Hotel_Management.services.admin.rooms;

import com.Hotel_Management.dto.RoomDto;
import com.Hotel_Management.dto.RoomsResponseDto;

public interface RoomsService {

	boolean postRoom(RoomDto roomDto);
	
	RoomsResponseDto getAllRooms(int pageNumber);
	
	RoomDto getRoomById(Long id);
	
	boolean updateRoom(Long id, RoomDto roomDto);
	
	void deleteRoom(Long id);
}
