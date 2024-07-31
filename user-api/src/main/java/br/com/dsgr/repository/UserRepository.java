package br.com.dsgr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.dsgr.model.User;

@Repository
public interface UserRepository	extends JpaRepository<User, Long> {
	
	UserDetails findByUsername(String username);
	
	@Query(value = "select u from User u where u.username = :username")
	User getByUsername(@Param("username")  String username);
	
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);
}
