package com.Hotel_Management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Hotel_Management.entity.User;
import com.Hotel_Management.enums.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findFirstByEmail(String email);
	
	Optional<User> findByUserRole(UserRole userRole); 
}
