package com.example.demo.model;

import java.time.LocalDate;

public class Viaje {

	private int id;
	private int idUsuario;
	private Monopatin monopatin;
	private Parada paradaInicio;
	private Parada paradaDestino;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private double distanciaEstimada;
	private double distanciaDesvio;
	private double precioFinal;
	private double precioEstimado;
	private double tarifaPausa;
	private double tarifaRegular;
	private int tiempoPausa;
	
	public Viaje() {
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

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

}