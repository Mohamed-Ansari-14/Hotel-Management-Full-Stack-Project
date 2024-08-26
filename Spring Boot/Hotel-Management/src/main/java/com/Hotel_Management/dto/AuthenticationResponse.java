package com.Hotel_Management.dto;

import com.Hotel_Management.enums.UserRole;

import lombok.Data;

@Data
public class AuthenticationResponse {

	private String jwt;
	
	private Long userId;
	
	private UserRole userRole;
}
