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
	private float tarifaRegular;
	
	@Column
	private float tarifaPausa;
	
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

	public float getTarifaRegular() {
		return tarifaRegular;
	}

	public void setTarifaRegular(float tarifaRegular) {
		this.tarifaRegular = tarifaRegular;
	}

	public float getTarifaPausa() {
		return tarifaPausa;
	}

	public void setTarifaPausa(float tarifaPausa) {
		this.tarifaPausa = tarifaPausa;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	

	
	
	

}
