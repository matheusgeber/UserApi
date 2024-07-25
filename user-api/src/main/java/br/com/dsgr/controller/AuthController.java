package br.com.dsgr.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dsgr.controller.dto.LoginRequestDto;
import br.com.dsgr.model.User;
import br.com.dsgr.service.UserService;

@RestController
@RequestMapping
public class AuthController {
	
	private UserService userService;
	
	@PostMapping("/signin")
	public ResponseEntity<User> loginUser(@RequestBody LoginRequestDto dto) {
		return null;
	}

}
