package com.app.bowlingservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {
	
    @GetMapping
    public ResponseEntity<String> getHealth(@RequestHeader(value = "User-Agent") String userAgent){
    	
        System.out.printf("User Agent: %s\n", userAgent);
        
        System.out.println("It's healthy!");
        
        return ResponseEntity.ok("It's healthy!");
    }
}