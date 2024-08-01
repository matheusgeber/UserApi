package br.com.dsgr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dsgr.model.UserMessage;

public interface UserMessageRepository extends JpaRepository<UserMessage, Long> {

}
