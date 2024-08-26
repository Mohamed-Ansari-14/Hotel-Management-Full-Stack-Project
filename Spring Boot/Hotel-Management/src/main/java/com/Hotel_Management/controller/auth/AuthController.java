package com.Hotel_Management.controller.auth;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Hotel_Management.dto.AuthenticationRequest;
import com.Hotel_Management.dto.AuthenticationResponse;
import com.Hotel_Management.dto.SignupRequest;
import com.Hotel_Management.dto.UserDto;
import com.Hotel_Management.entity.User;
import com.Hotel_Management.repository.UserRepository;
import com.Hotel_Management.services.auth.AuthService;
import com.Hotel_Management.services.jwt.UserService;
import com.Hotel_Management.util.JwtUtil;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	
	private final AuthenticationManager authenticationManager;
	
	private final UserRepository userRepository;
	
	private final JwtUtil jwtUtil;
	
	private final UserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest){
		try {
			UserDto createdUser = authService.createUser(signupRequest);
			return new ResponseEntity<>(createdUser, HttpStatus.OK);
		}
		catch(EntityExistsException entityExistsException) {
			return new ResponseEntity<>("User already exists", HttpStatus.NOT_ACCEPTABLE);
		}
		catch(Exception e) {
			return new ResponseEntity<>("User not created, come again later", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/login")
	public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getEmail(), authenticationRequest.getPassword()));
		}
		catch(BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect username or password.");
		}
		
		final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
		Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);
		
		AuthenticationResponse authenticationResponse = new AuthenticationResponse();
		if(optionalUser.isPresent()) {
			authenticationResponse.setJwt(jwt);
			authenticationResponse.setUserRole(optionalUser.get().getUserRole());
			authenticationResponse.setUserId(optionalUser.get().getId());
		}
		
		return authenticationResponse;
	}
}




















