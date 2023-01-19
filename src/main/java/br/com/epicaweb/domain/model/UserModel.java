package br.com.epicaweb.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserModel {
	@Id
	private Long id;
	private String username;	
}
