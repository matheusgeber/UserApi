package br.com.dsgr.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_messages")
public class UserMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User userId;

	@Column(nullable = false)
	private String message;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;

	public UserMessage(User userId, String message, Date timestamp) {
		super();
		this.userId = userId;
		this.message = message;
		this.timestamp = timestamp;
	}

}
