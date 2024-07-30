package br.com.dsgr.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "username"),
				@UniqueConstraint(columnNames = "email")
		})
public class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	
	private String firstName;

	private String lastName;
	
	private String email;
	
	private String cpfCnpj; 
	
	private String password;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date birthday;
	
	private Date registrationTime;
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_roles",
				joinColumns = @JoinColumn(name = "user_id"),
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles = new ArrayList<Role>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	        	
		for(Role role : roles) {
				if (role.getName().name().equals(UserRole.ROLE_MANAGER.name())) {
		            return List.of(new SimpleGrantedAuthority("ROLE_MANAGER"));
		
		        } else if (role.getName().name().equals(UserRole.ROLE_ADMIN.name())) {
		            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
		
		        } else if(role.getName().name().equals(UserRole.ROLE_BASIC.name())) {
		            return List.of(new SimpleGrantedAuthority("ROLE_BASIC"));
		        } 
				    	
		}	
		return null;
	}
	
}
