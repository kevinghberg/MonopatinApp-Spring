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
	}

	public Tarifa(int tarifaRegular, int tarifaPausa) {
		this.tarifaRegular = tarifaRegular;
		this.tarifaPausa = tarifaPausa;
		this.fecha = LocalDate.now();
	}

	public int getIdTarifa() {
		return idTarifa;
	}

	public void setIdTarifa(int idTarifa) {
		this.idTarifa = idTarifa;
	}

	public int getTarifaRegular() {
		return tarifaRegular;
	}

	public void setTarifaRegular(int tarifaRegular) {
		this.tarifaRegular = tarifaRegular;
	}

	public int getTarifaPausa() {
		return tarifaPausa;
	}

	public void setTarifaPausa(int tarifaPausa) {
		this.tarifaPausa = tarifaPausa;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	

	
	
	

}
