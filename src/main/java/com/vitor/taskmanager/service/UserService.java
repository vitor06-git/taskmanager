package com.vitor.taskmanager.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vitor.taskmanager.model.User;
import com.vitor.taskmanager.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	private PasswordEncoder passwordEncoder;
	
	public User register(User user) {
		if (userRepository.existsByEmail(user.getEmail())) {
			throw new RuntimeException("Email already registered");
			
			
		}
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		return userRepository.save(user);
	}
	
	public Optional<User>findByEmail(String email){
		return userRepository.findByEmail(email);
		
	}
	
	public Optional<User>findById(Long id){
		return  userRepository.findById(id);
	}

}
