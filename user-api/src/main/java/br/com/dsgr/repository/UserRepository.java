package br.com.dsgr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dsgr.model.User;

@Repository
public interface UserRepository	extends JpaRepository<User, Long> {
	
}
