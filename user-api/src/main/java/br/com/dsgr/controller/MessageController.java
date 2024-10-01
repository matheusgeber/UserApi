package br.com.dsgr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dsgr.security.services.TokenService;
import br.com.dsgr.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/teste")
@SecurityRequirement(name = "bearerAuth")
public class MessageController {

	@Autowired
	TokenService tokenService;

	@Autowired
	UserService userService;

	@PostMapping("/basic")
	@Operation(summary = "Basic test")
	public ResponseEntity<String> basicTest(@RequestBody String message,
			Authentication authentication) {
		try {
			String username = authentication.getName();
			System.out.println("user: " + username);
			userService.saveMessage(message, username);

			return ResponseEntity.ok("Teste basico aprovado!");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/admin")
	@Operation(summary = "Admin test")
	public ResponseEntity<String> adminTest(@RequestBody String message,
			Authentication authentication) {
		try {
			String username = authentication.getName();
			System.out.println("user: " + username);
			userService.saveMessage(message, username);

			return ResponseEntity.ok("Teste de admin aprovado!");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/manager")
	@Operation(summary = "Manager test")
	public ResponseEntity<String> managerTest(@RequestBody String message,
			Authentication authentication) {
		try {
			String username = authentication.getName();
			System.out.println("user: " + username);
			userService.saveMessage(message, username);

			return ResponseEntity.ok("Teste de manager aprovado!");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
