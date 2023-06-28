package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.demo.security.PasswordUtils;

import io.swagger.annotations.ApiModelProperty;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Data
public class Administrador {

	@Id
	@ApiModelProperty(hidden = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String usuario;
	
	@Column
	private String password;

	public Administrador() {
	}

	public Administrador(String usuario, String password) {
		this.usuario = usuario;
		this.password = PasswordUtils.hashPassword(password);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String get() {
		return password;
	}

	public void set(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}

}
