package com.Hotel_Management.dto;

import lombok.Data;

@Data
public class SignupRequest {

	private String email;
	
	private String password;
	
	private String name;
}
