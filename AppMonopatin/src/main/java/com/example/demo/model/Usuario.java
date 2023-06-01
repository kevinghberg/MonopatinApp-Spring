package com.example.demo.model;

import java.util.List;
import java.util.Set;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "usuario")
public class Usuario {

	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUsuario;
	
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "usuario")
    private Viaje viaje;
    
    @ManyToOne(fetch = FetchType.LAZY)
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
	private boolean estadoCuentaAnulada;

	public Usuario() {
	}

	public Usuario(String nombre, String apellido, String celular, String email) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.celular = celular;
		this.email = email;
		this.estadoCuentaAnulada = false;
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

	/*public Set<CuentaMercadoPago> getListaMP() {
		return listaMP;
	}

	public void setListaMP(Set<CuentaMercadoPago> listaMP) {
		this.listaMP = listaMP;
	}*/

}
