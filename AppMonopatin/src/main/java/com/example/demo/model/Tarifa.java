package com.example.demo.model;

import java.time.LocalDate;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Tarifa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTarifa;
	
	@Column
	private int tarifaRegular;
	
	@Column
	private int tarifaPausa;
	
	@Column
	private LocalDate fecha;

	public Tarifa() {
		super();
	}

	public Tarifa(int tarifaRegular, int tarifaPausa, LocalDate fecha) {
		super();
		this.tarifaRegular = tarifaRegular;
		this.tarifaPausa = tarifaPausa;
		this.fecha = fecha;
	}
	

	
	
	

}
