package com.vitor.taskmanager.contoroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitor.taskmanager.dto.LoginDTO;
import com.vitor.taskmanager.dto.TokenDTO;
import com.vitor.taskmanager.service.AuthenticationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	@Autowired
	private AuthenticationService authenticationService;
	
	
	@PostMapping("/login")
	public TokenDTO login(@RequestBody @Valid LoginDTO dto) {
		return authenticationService.login(dto);
	}
}
