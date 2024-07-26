package br.com.dsgr.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.dsgr.model.User;

@Repository
public interface UserRepository	extends JpaRepository<User, Long> {
	
	UserDetails findByUsername(String username);
	
	
	@Query("SELECT u FROM User u WHERE u.username = :param")
	Optional<User> getUserByUsername(@Param("param")  String username);
	
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);
}
