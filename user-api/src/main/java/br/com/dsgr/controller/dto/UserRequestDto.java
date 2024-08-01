package br.com.dsgr.controller.dto;

import java.util.Date;
import java.util.Set;

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
	
	/* TODO AVALIAR FORMATO DATE RETORNADO
	 * TENTAR PADRONIZAR DD/MM/YYYY
	 * @JsonFormat(pattern = "dd/MM/yyyy") */
	private Date birthday;
}
