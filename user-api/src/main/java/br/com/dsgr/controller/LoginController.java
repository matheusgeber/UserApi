package br.com.dsgr.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dsgr.controller.dto.LoginRequestDto;
import br.com.dsgr.model.User;
import br.com.dsgr.service.UserService;

@RestController
@RequestMapping
public class LoginController {
	
	private AuthenticationManager authenticationManager;
	
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody LoginRequestDto data) {
		var usernamePassword= new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		return ResponseEntity.ok().build();
	}

}
