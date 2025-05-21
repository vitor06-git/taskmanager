package com.vitor.taskmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.vitor.taskmanager.dto.LoginDTO;
import com.vitor.taskmanager.dto.TokenDTO;
import com.vitor.taskmanager.model.User;
import com.vitor.taskmanager.security.JwtUtil;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    public TokenDTO login(LoginDTO dto) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );

        User user = (User) authentication.getPrincipal();

        String token = jwtUtil.generateToken(user.getEmail());// usa o email

        return new TokenDTO(token);
    }
}

