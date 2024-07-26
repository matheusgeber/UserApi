package br.com.dsgr.controller.dto;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.dsgr.model.Role;
import br.com.dsgr.model.User;
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

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date birthday;
	
	private String idade;
	
	private Date registrationTime;
	
}
