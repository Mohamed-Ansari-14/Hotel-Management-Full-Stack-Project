package com.Hotel_Management.services.auth;

import com.Hotel_Management.dto.SignupRequest;
import com.Hotel_Management.dto.UserDto;

public interface AuthService {

	UserDto createUser(SignupRequest signupRequest);
}
