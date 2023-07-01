package com.example.demo.model;

import java.time.LocalDate;

import javax.persistence.*;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Data
@Table(name = "viaje")
public class Viaje {

	@Id
	@Column(name = "id")
	@ApiModelProperty(hidden = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private Integer idUsuario;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "monopatin_id")
	@ApiModelProperty(hidden = true)
	private Monopatin monopatin;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parada_inicio_id")
	private Parada paradaInicio;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parada_destino_id")
	private Parada paradaDestino;

	@Column
	@ApiModelProperty(hidden = true)
	private LocalDate fechaInicio;

	@Column
	@ApiModelProperty(hidden = true)
	private LocalDate fechaFin;

	@Column
	@ApiModelProperty(hidden = true)
	private double distanciaEstimada;
	
	@Column
	@ApiModelProperty(hidden = true)
	private double distanciaDesvio;

	@Column
	@ApiModelProperty(hidden = true)
	private double precioFinal;

	@Column
	@ApiModelProperty(hidden = true)
	private double precioEstimado;
	
	@Column
	@ApiModelProperty(hidden = true)
	private double tarifaPausa;
	
	@Column
	@ApiModelProperty(hidden = true)
	private double tarifaRegular;

	@Column
	@ApiModelProperty(hidden = true)
	private int tiempoPausa;
	
	public Viaje() {
	}

	public int getIdViaje() {
		return id;
	}

	public void setIdViaje(int id) {
		this.id = id;
	}
	
	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Monopatin getMonopatin() {
		return monopatin;
	}

	public void setMonopatin(Monopatin monopatin) {
		this.monopatin = monopatin;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public double getTarifaPausa() {
		return tarifaPausa;
	}

	public void setTarifaPausa(double tarifaPausa) {
		this.tarifaPausa = tarifaPausa;
	}

	public double getPrecioFinal() {
		return precioFinal;
	}

	public void setPrecioFinal(double d) {
		this.precioFinal = d;
	}

	public double getPrecioEstimado() {
		return precioEstimado;
	}

	public void setPrecioEstimado(double precioEstimado2) {
		this.precioEstimado = precioEstimado2;
	}

	public int getTiempoPausa() {
		return tiempoPausa;
	}

	public void setTiempoPausa(int tiempoPausa) {
		this.tiempoPausa = tiempoPausa;
	}



	public double getDistanciaEstimada() {
		return distanciaEstimada;
	}

	public void setDistanciaEstimada(double distanciaEstimada) {
		this.distanciaEstimada = distanciaEstimada;
	}

	public double getDistanciaDesvio() {
		return distanciaDesvio;
	}
	
	public void setDistanciaDesvio(double distanciaDesvio) {
		this.distanciaDesvio = distanciaDesvio;
	}
	
	public double getTarifaRegular() {
		return tarifaRegular;
	}

	public void setTarifaRegular(double tarifaRegular) {
		this.tarifaRegular = tarifaRegular;
	}

	public int getId() {
		return id;
	}
	
	public Parada getParadaInicio() {
		return paradaInicio;
	}

	public void setParadaInicio(Parada paradaInicio) {
		this.paradaInicio = paradaInicio;
	}

	public Parada getParadaDestino() {
		return paradaDestino;
	}

	public void setParadaDestino(Parada paradaDestino) {
		this.paradaDestino = paradaDestino;
	}

}