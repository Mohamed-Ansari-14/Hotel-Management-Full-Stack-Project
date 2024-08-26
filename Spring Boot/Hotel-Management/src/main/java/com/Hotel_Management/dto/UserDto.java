package com.Hotel_Management.dto;

import com.Hotel_Management.enums.UserRole;

import lombok.Data;

@Data
public class UserDto {

	private Long id;

	private String email;

	private String name;
	
	private UserRole userRole;
}
