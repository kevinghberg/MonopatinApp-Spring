package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Data
public class CuentaMercadoPago {

	@Id
	@ApiModelProperty(hidden = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private double saldo;
		
	@Column
	@ApiModelProperty(hidden = true)
	private LocalDate fechaAlta;
	
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
	private List<Usuario> listaUsuario;

	public CuentaMercadoPago() {
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public CuentaMercadoPago(double saldo) {
		this.saldo = saldo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

}
