package com.vitor.taskmanager.contoroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {
	
	
	@GetMapping("/protected")
	public String protectedEndpoint() {
		return "você está autenditaco!";
	}

}
