package br.com.dsgr.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.dsgr.controller.dto.UserRequestDto;
import br.com.dsgr.controller.dto.UserResponseDto;
import br.com.dsgr.model.User;
import br.com.dsgr.repository.UserRepository;
import br.com.dsgr.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/accounts")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	// listar todos usuarios
	@GetMapping
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	// listar usuario por id
	@GetMapping("/{id}")
	public Optional<User> getUserById(@PathVariable Long id) {
		return userRepository.findById(id);
	}

	// SignupRequest
	@PostMapping
	public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto dto) {
		try {
			UserResponseDto user = userService.createUser(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(user);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateUser(@RequestBody UserRequestDto dto, @PathVariable(name = "id") Long id) {
		try {
			User user = userService.updateUser(dto, id);
			return ResponseEntity.ok().body(user);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}

	}

	@PutMapping("/role/{id}")
	public ResponseEntity<?> updateRole(@RequestParam(name = "role") String role, @PathVariable(name = "id") Long id) {
		userService.updateRole(role, id);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		try {
			userService.deleteUser(id);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.noContent().build();
	}

}
