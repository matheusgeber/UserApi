package br.com.dsgr.controller.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
	
	private String username;
	
	private String name;
	
	private String email;
	
	private String cpfCnpj; 
	
	private String password;

	private Date birthday;
	
	private String idade;
	
	private Date registrationTime;
	
}
