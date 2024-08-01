package br.com.dsgr.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.dsgr.controller.dto.UserRequestDto;
import br.com.dsgr.controller.dto.UserResponseDto;
import br.com.dsgr.model.Role;
import br.com.dsgr.model.User;
import br.com.dsgr.model.UserMessage;
import br.com.dsgr.model.UserRole;
import br.com.dsgr.repository.RoleRepository;
import br.com.dsgr.repository.UserMessageRepository;
import br.com.dsgr.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired UserMessageRepository userMessageRepository;
	
	public List<User> getAllUsers(UserResponseDto userResponse) {
		List<User> list = userRepository.findAllUsers();
		return list;
	}

	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);	
	}
	
	public Boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}
	
	public Boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}
	
	public UserResponseDto createUser(UserRequestDto dto) throws Exception {

		if (dto.getUsername() == null || dto.getUsername().isBlank() || dto.getUsername().length() < 4
				|| dto.getUsername().length() > 14) {
			throw new Exception();
		}

		if (existsByUsername(dto.getUsername())) {
			throw new Exception();

		}

		if (dto.getPassword() == null || dto.getPassword().isBlank() || dto.getPassword().length() < 6) {
			throw new Exception();
		}

		if (dto.getFirstName() == null || dto.getFirstName().isBlank()) {
			throw new Exception();
		}

		if (dto.getLastName() == null || dto.getLastName().isBlank()) {
			throw new Exception();
		}

		if (dto.getEmail() == null || dto.getEmail().isBlank()) {
			throw new Exception();
		}

		if (existsByEmail(dto.getEmail())) {
			throw new Exception();
		}

		
		//lembrete: validar a data 
		if (dto.getBirthday() == null || dto.getEmail().isBlank()) { 
		throw new Exception(); }
		 

		
		if (dto.getCpfCnpj() == null || dto.getCpfCnpj().isBlank()){ 
		 	throw new Exception(); }
		
		
		String encryptedPassword = new BCryptPasswordEncoder().encode(dto.getPassword());
		
		User user = new User();
		user.setUsername(dto.getUsername());
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setEmail(dto.getEmail());
		user.setCpfCnpj(dto.getCpfCnpj());
		user.setPassword(encryptedPassword);
		user.setBirthday(dto.getBirthday());
		List<Role> roles = new ArrayList<Role>();
		Role role = new Role();
		role.setName(UserRole.ROLE_BASIC);
		roles.add(role);
		user.setRoles(roles);
		user = userRepository.save(user);
		
		/*UserResponseDto userResponse = new UserResponseDto();
		userResponse.setBirthday(user.getBirthday());
		userResponse.setName(user.getFirstName() + " " +user.getLastName());
		userResponse.setEmail(user.getEmail());
		userResponse.setUsername(user.getUsername());
		return userResponse;*/
		return UserResponseDto.builder().birthday(user.getBirthday()).name(user.getFirstName()).
				email(user.getEmail()).build();
		
	}

	public User updateUser(UserRequestDto dto, Long id) throws Exception {

		Optional<User> userOpt = userRepository.findById(id);
		if (userOpt.isPresent()) {

			User user = userOpt.get();
			user.setUsername(dto.getUsername());
			user.setFirstName(dto.getFirstName());
			user.setLastName(dto.getLastName());
			// user.setEmail(dto.getEmail());
			user.setCpfCnpj(dto.getCpfCnpj());
			user.setPassword(dto.getPassword());
			user.setBirthday(dto.getBirthday());
			return userRepository.save(user);
		}

		throw new Exception();
	}
	
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	public void updateRole(String role, Long id) {
		Optional<User> userOpt = userRepository.findById(id);
		User user = userOpt.isPresent() ? userOpt.get() : null;
		/*Arrays.asList(UserRole.values()).forEach(value -> {
			log.info(value.toString());
			log.info(role);
			log.info(Boolean.toString(value.toString().equals(role)));
			log.info("============================================================");
			if (value.toString().equals(role)) {
				List<Role> list = new ArrayList<Role>();
				Role roleU = new Role();
				roleU.setName(value);
				list.add(roleU);
				user.setRoles(list);
				userRepository.save(user);
			}
		});    */
		
		List<UserRole> mainList = Arrays.asList(UserRole.values());
		for(int i = 0; i < mainList.size() ; i++) {
			UserRole userRole = mainList.get(i);
			if(role.equals(userRole.toString())) {
				
				List<Role> list = new ArrayList<Role>();
				Role roleU = new Role();
				roleU.setName(userRole);
				list.add(roleU);
				user.setRoles(list);
				userRepository.save(user);
			}
		}
			
	}
	
	public void saveMessage(String message, String username) {
		User userOpt = userRepository.getByUsername(username);
        UserMessage userMessage = new UserMessage(userOpt, message, new Date());
        userMessage.setUserId(userOpt);
        userMessageRepository.save(userMessage);
	
    }
}
