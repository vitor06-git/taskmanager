package com.vitor.taskmanager.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vitor.taskmanager.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	
	@Autowired
	private JwtUtil jwtUtil;

	
	@Autowired
	private UserService userService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
					            HttpServletResponse response,
					            FilterChain filterChain)
		throws ServletException, IOException{
		
		
	final String authHeader = request.getHeader("Authorization");
	
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        filterChain.doFilter(request, response);
        return;
        
    	}
    	
    final String jwt = authHeader.substring(7);
    final String email = jwtUtil.extractUsername(jwt);
    
    if (email != null && SecurityContextHolder.getContext().getAuthentication() == null ) {
    	var userOpt = userService.findByEmail(email);
    	if(userOpt.isPresent() && jwtUtil.isTokenValid(jwt)) {
        	var user = userOpt.get();
        	var authToken = new UsernamePasswordAuthenticationToken(
        			user, null, user.getAuthorities()
        			);
        	authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        	SecurityContextHolder.getContext().setAuthentication(authToken);
        	
    		}
    	
    	}
    
    filterChain.doFilter(request, response);
    
	}
}
