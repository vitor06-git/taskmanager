package com.vitor.taskmanager.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	private static final String SECRET_KEY = "meuSegredoUltraSeguroParaJWTDeExemplo1234567890";
	private static final long EXPIRATION_TIME = 86400000;
	
	private Key getSigningKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
		
	}
	
	public String generateToken(String email) {
		return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
				
	}
	
	
	public String extractUsername(String token) {
		return parseToken(token).getBody().getSubject();
	}
	
	public boolean isTokenValid(String token) {
		try {
			parseToken(token);
			return true;
			
		} catch (JwtException | IllegalArgumentException e) {
			return false;
			
			
		}
	}
	
	private Jws<Claims> parseToken(String token){
		return Jwts.parserBuilder()
		          .setSigningKey(getSigningKey())
	                .build()
	                .parseClaimsJws(token);
	}
		
	
	
	

}
