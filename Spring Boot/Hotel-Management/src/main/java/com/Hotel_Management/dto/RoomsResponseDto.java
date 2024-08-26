package com.Hotel_Management.dto;

import java.util.List;

import lombok.Data;

@Data
public class RoomsResponseDto {

	private List<RoomDto> roomDtoList;
	
	private Integer totalPages;
	
	private Integer pageNumber;
}
