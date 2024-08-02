package br.com.dsgr.controller.dto;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date registrationTime;
	
}
