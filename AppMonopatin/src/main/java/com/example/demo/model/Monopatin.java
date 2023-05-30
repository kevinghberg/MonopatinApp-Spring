package com.example.demo.model;

import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Monopatin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMonopatin;
	
	@Column
	List<Parada> paradasPermitidas;
	
	@Column
	private boolean enUso;
	
	@Column
	private boolean estadoMantenimiento;
	
	@Column
	private int tiempoUso;
	
	@Column
	private String patente;
	
	public Monopatin() {
		super();
	}

	public Monopatin(String patente) {
		super(); 
		this.patente = patente;
	}

	

}
