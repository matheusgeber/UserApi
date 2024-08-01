package br.com.dsgr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.dsgr.model.User;

@Repository
public interface UserRepository	extends JpaRepository<User, Long> {
	
	
	UserDetails findByUsername(String username);
	
	@Query("select u from User u")
	List<User> findAllUsers();
	
	@Query(value = "select u from User u where u.username = :username")
	User getByUsername(@Param("username")  String username);
	
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);
}
