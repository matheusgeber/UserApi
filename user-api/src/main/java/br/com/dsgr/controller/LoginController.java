package br.com.dsgr.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dsgr.controller.dto.LoginRequestDto;
import br.com.dsgr.model.User;
import br.com.dsgr.security.services.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/accounts")
@Tag(name = "LOGIN")
public class LoginController {
	
	private TokenService tokenService;
	
	private AuthenticationManager authenticationManager;
	
	public LoginController(TokenService tokenService, AuthenticationManager authenticationManager) {
		super();
		this.tokenService = tokenService;
		this.authenticationManager = authenticationManager;
	}

	@PostMapping("/login")
	@Operation(summary = "Enter your account")
	@ApiResponses(value = {
	@ApiResponse(responseCode = "200", description = "Login completed")})
	public ResponseEntity<String> username(@RequestBody LoginRequestDto dto) {
		UsernamePasswordAuthenticationToken usernamePassword= new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
		Authentication auth = this.authenticationManager.authenticate(usernamePassword);
		
		String token = tokenService.generateToken((User) auth.getPrincipal());
		
		return ResponseEntity.ok(token);
	}
}
