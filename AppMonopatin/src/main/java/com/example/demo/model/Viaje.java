package com.example.demo.model;

import java.time.LocalDate;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "viaje")
public class Viaje {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "monopatin_id")
	private Monopatin monopatin;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parada_inicio_id")
	private Parada paradaInicio;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parada_destino_id")
	private Parada paradaDestino;

	@Column
	private LocalDate fechaInicio;

	@Column
	private LocalDate fechaFin;

	@Column
	private double distanciaEstimada;
	
	@Column
	private double distanciaDesvio;

	@Column
	private double precioFinal;

	@Column
	private double precioEstimado;
	
	@Column
	private double tarifaPausa;
	
	@Column
	private double tarifaRegular;

	@Column
	private int tiempoPausa;
	
	public Viaje() {
	}

	public Viaje(Monopatin monopatin) {
		this.monopatin = monopatin;
	}

	public int getIdViaje() {
		return id;
	}

	public void setIdViaje(int id) {
		this.id = id;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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