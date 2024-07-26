package br.com.dsgr.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.dsgr.model.User;
import br.com.dsgr.repository.UserRepository;

public class AuthorizationService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public Optional<User> loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
		
	}

}
