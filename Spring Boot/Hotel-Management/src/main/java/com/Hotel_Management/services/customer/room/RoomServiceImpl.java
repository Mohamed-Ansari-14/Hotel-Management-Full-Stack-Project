package com.Hotel_Management.services.customer.room;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Hotel_Management.dto.RoomsResponseDto;
import com.Hotel_Management.entity.Room;
import com.Hotel_Management.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

	private final RoomRepository roomRepository;

	public RoomsResponseDto getAvailableRooms(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber, 6);
		Page<Room> roomPage = roomRepository.findByAvailable(true, pageable);

		RoomsResponseDto roomsResponseDto = new RoomsResponseDto();
		roomsResponseDto.setPageNumber(roomPage.getPageable().getPageNumber());
		roomsResponseDto.setTotalPages(roomPage.getTotalPages());
		roomsResponseDto.setRoomDtoList(roomPage.stream().map(Room::getRoomDto).collect(Collectors.toList()));

		return roomsResponseDto;
	}
}












