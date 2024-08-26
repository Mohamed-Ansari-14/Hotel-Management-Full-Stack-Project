package com.Hotel_Management.services.admin.rooms;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Hotel_Management.dto.RoomDto;
import com.Hotel_Management.dto.RoomsResponseDto;
import com.Hotel_Management.entity.Room;
import com.Hotel_Management.repository.RoomRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomsServiceImpl implements RoomsService{

	private final RoomRepository roomRepository;
	
	public boolean postRoom(RoomDto roomDto) {
		try {
			Room room = new Room();
			
			room.setName(roomDto.getName());
			room.setPrice(roomDto.getPrice());
			room.setType(roomDto.getType());
			room.setAvailable(true);
			
			roomRepository.save(room);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public RoomsResponseDto getAllRooms(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber, 6);
		Page<Room> roomPage = roomRepository.findAll(pageable);
		
		RoomsResponseDto roomsResponseDto = new RoomsResponseDto();
		roomsResponseDto.setPageNumber(roomPage.getPageable().getPageNumber());
		roomsResponseDto.setTotalPages(roomPage.getTotalPages());
		roomsResponseDto.setRoomDtoList(roomPage.stream().map(Room::getRoomDto).collect(Collectors.toList()));
		
		return roomsResponseDto;
	}
	
	public RoomDto getRoomById(Long id) {
		Optional<Room> optionalRoom = roomRepository.findById(id);
		
		if(optionalRoom.isPresent()) {
			return optionalRoom.get().getRoomDto();
		}else {
			throw new EntityNotFoundException("Room not present.");
		}
	}
	
	public boolean updateRoom(Long id, RoomDto roomDto) {
		Optional<Room> optionalRoom = roomRepository.findById(id);
		
		if(optionalRoom.isPresent()) {
			Room existingRoom = optionalRoom.get();
			
			existingRoom.setName(roomDto.getName());
			existingRoom.setPrice(roomDto.getPrice());
			existingRoom.setType(roomDto.getType());
			
			roomRepository.save(existingRoom);
			return true;
		}
		return false;
	}
	
	public void deleteRoom(Long id) {
		Optional<Room> optionalRoom = roomRepository.findById(id);
		
		if(optionalRoom.isPresent()) {
			roomRepository.deleteById(id);
		}else {
			throw new EntityNotFoundException("Room not present.");
		}
	}
}












