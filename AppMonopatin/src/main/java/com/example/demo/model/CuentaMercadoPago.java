package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class CuentaMercadoPago {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private double saldo;
	
	@Column
	private LocalDate fechaAlta ;
	
	@ManyToMany(mappedBy = "listaMP", fetch = FetchType.LAZY)
    private List<Usuario> listaUsuarios;
 
	public CuentaMercadoPago() {
		super();
	}

	public CuentaMercadoPago(double saldo, LocalDate fechaAlta) {
		super();
		this.saldo = saldo;
		this.fechaAlta = fechaAlta;
	}
	
	
	
}
