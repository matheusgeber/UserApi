package br.com.dsgr.controller.dto;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Past;
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
public class UserRequestDto {
	
	private String username;
	
	private String firstName;

	private String lastName;
	
	private String email;
	
	private String cpfCnpj; 
	
	private String password;
	
	private Set<String> role;
	
	@Past
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date birthday;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date registrationTime;
}
