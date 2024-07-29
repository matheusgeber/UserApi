	package br.com.dsgr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dsgr.controller.dto.LoginRequestDto;
import br.com.dsgr.model.User;
import br.com.dsgr.security.services.TokenService;

@RestController
@RequestMapping
public class LoginController {
	
	@Autowired
	TokenService tokenService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<String> username(@RequestBody LoginRequestDto dto) {
		var usernamePassword= new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		
		String token = tokenService.generateToken((User) auth.getPrincipal());
		
		return ResponseEntity.ok(token);
	}

}
