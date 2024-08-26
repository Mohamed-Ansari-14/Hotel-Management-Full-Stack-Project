package com.Hotel_Management.services.auth;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Hotel_Management.dto.SignupRequest;
import com.Hotel_Management.dto.UserDto;
import com.Hotel_Management.entity.User;
import com.Hotel_Management.enums.UserRole;
import com.Hotel_Management.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

	private final UserRepository userRepository;
	
	@PostConstruct
	public void createAnAdminAccount() {
		Optional<User> adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
		if(adminAccount.isEmpty()) {
			User user = new User();
			user.setEmail("admin@test.com");
			user.setName("Admin");
			user.setUserRole(UserRole.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);
			System.out.println("Admin account created successfully!!!");
			
		}else {
			System.out.println("Admin account already created!!!");
		}
	}
	
	public UserDto createUser(SignupRequest signupRequest) {
		if(userRepository.findFirstByEmail(signupRequest.getEmail()).isPresent()) {
			throw new EntityExistsException("User already present with email "+ signupRequest.getEmail());
		}
		
		User user = new User();
		user.setEmail(signupRequest.getEmail());
		user.setName(signupRequest.getName());
		user.setUserRole(UserRole.CUSTOMER);
		user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
		
		User createdUser = userRepository.save(user);
		return createdUser.getUserDto();
	}
}











