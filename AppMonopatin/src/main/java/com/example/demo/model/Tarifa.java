package com.example.demo.model;

import java.time.LocalDate;

import javax.persistence.*;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Data
public class Tarifa {

	@Id
	@ApiModelProperty(hidden = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTarifa;

	@Column
	private double tarifaRegular;

	@Column
	private double tarifaPausa;

	@Column
	@ApiModelProperty(hidden = true)
	private LocalDate fecha;

	public Tarifa() {
	}

	public Tarifa(double d, double tarifaPausa) {
		this.tarifaRegular = d;
		this.tarifaPausa = tarifaPausa;
		this.fecha = LocalDate.now();
	}

	public int getIdTarifa() {
		return idTarifa;
	}

	public void setIdTarifa(int idTarifa) {
		this.idTarifa = idTarifa;
	}

	public double getTarifaRegular() {
		return tarifaRegular;
	}

	public void setTarifaRegular(double tarifaRegular) {
		this.tarifaRegular = tarifaRegular;
	}

	public double getTarifaPausa() {
		return tarifaPausa;
	}

	public void setTarifaPausa(double tarifaPausa) {
		this.tarifaPausa = tarifaPausa;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

}
