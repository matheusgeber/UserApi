package br.com.dsgr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dsgr.security.services.TokenService;
import br.com.dsgr.service.UserService;

@RestController
@RequestMapping("/teste")
public class MessageController {
	
	@Autowired
	TokenService tokenService;
	
	@Autowired
	UserService userService;

	@PostMapping("/basic")
    public ResponseEntity<String> postMessage(@RequestBody String message, @RequestHeader(name = "Authorization") String token ) {
        try {
        	String username = tokenService.extractUsername(token);
        	System.out.println("OK" +username);
         	userService.saveMessage(message, username);
			
            return ResponseEntity.ok("Passou");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
	}
	
	
}
