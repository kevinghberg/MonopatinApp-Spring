package com.example.demo.model;

import javax.persistence.*;

import com.example.demo.security.PasswordUtils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Data
@Table(name = "usuario")
public class Usuario {

	@Id
	@Column(name = "id")
	@ApiModelProperty(hidden = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUsuario;

	@ManyToOne(fetch = FetchType.EAGER)
	@ApiModelProperty(hidden = true)
	private CuentaMercadoPago cuentamp;

	@Column
	private String nombre;

	@Column
	private String apellido;

	@Column
	private String celular;

	@Column(unique = true)
	private String email;
	
	@Column
	@ApiModelProperty(hidden = true)
	private String password;
	
	@Column
	@ApiModelProperty(hidden = true)
	private boolean estadoCuentaAnulada;

	@Column
	@ApiModelProperty(hidden = true)
	private boolean enViaje;

	public Usuario() {
	}

	public Usuario(String nombre, String apellido, String celular, String email, String password) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.celular = celular;
		this.email = email;
		this.estadoCuentaAnulada = false;
		this.password = PasswordUtils.hashPassword(password);
	}

	public Usuario(String nombre) {
		this.nombre = nombre;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEstadoCuentaAnulada() {
		return estadoCuentaAnulada;
	}

	public void setEstadoCuentaAnulada(boolean estadoCuentaAnulada) {
		this.estadoCuentaAnulada = estadoCuentaAnulada;
	}

	public boolean isEnViaje() {
		return enViaje;
	}

	public void setEnViaje(boolean enViaje) {
		this.enViaje = enViaje;
	}
	
	public String getPassword() {
		return password;
	}

}
