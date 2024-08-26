package com.Hotel_Management.services.customer.room;

import com.Hotel_Management.dto.RoomsResponseDto;

public interface RoomService {

	RoomsResponseDto getAvailableRooms(int pageNumber);
}
