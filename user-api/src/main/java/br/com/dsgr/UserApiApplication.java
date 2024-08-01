package br.com.dsgr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;


@SecurityScheme(
	    name = "bearerAuth",
	    scheme = "bearer",
	    bearerFormat = "JWT", 
	    type = SecuritySchemeType.HTTP, 
	    in = SecuritySchemeIn.HEADER
	)
@SpringBootApplication
public class UserApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApiApplication.class, args);
			
	}

}
