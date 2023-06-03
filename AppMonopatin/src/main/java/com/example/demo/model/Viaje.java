package com.example.demo.model;

import java.time.LocalDate;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Viaje {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idViaje;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "monopatin_id")
	private Monopatin monopatin;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parada_id")
	private Parada paradaInicio;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parada_id")
	private Parada paradaDestino;
	
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

	@Column
	private LocalDate fechaInicio;

	@Column
	private LocalDate fechaFin;

	@Column
	private double distanciaEstimada;
	
	@Column
	private double distanciaRecorrida;

	@Column
	private boolean estadoPausa;

	@Column
	private double precioFinal;

	@Column
	private double precioEstimado;

	@Column
	private double precioRecorridoActual;

	@Column
	private int tiempoPausa;
	
	

	public Viaje() {
	}

	public Viaje(Monopatin monopatin, Usuario usuario, LocalDate fechaInicio, LocalDate fechaFin, double distanciaRecorrida,
			double distanciaEstimada, boolean estadoPausa, long precioFinal, long precioEstimado, long precioRecorridoActual,
			int tiempoPausa) {
		this.monopatin = monopatin;
		this.usuario = usuario;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.distanciaRecorrida = distanciaRecorrida;
		this.distanciaEstimada = distanciaEstimada;
		this.estadoPausa = estadoPausa;
		this.precioFinal = precioFinal;
		this.precioEstimado = precioEstimado;
		this.precioRecorridoActual = precioRecorridoActual;
		this.tiempoPausa = tiempoPausa;
	}

	public Viaje(Monopatin monopatin) {
		this.monopatin = monopatin;
	}

	public int getIdViaje() {
		return idViaje;
	}

	public void setIdViaje(int idViaje) {
		this.idViaje = idViaje;
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

	public boolean isEstadoPausa() {
		return estadoPausa;
	}

	public void setEstadoPausa(boolean estadoPausa) {
		this.estadoPausa = estadoPausa;
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

	public double getPrecioRecorridoActual() {
		return precioRecorridoActual;
	}

	public void setPrecioRecorridoActual(double precioRecorridoActual) {
		this.precioRecorridoActual = precioRecorridoActual;
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

	public double getDistanciaRecorrida() {
		return distanciaRecorrida;
	}

	public void setDistanciaRecorrida(double distanciaRecorrida) {
		this.distanciaRecorrida = distanciaRecorrida;
	}

	@Override
	public String toString() {
		return "Viaje [id:" + idViaje + ", usuario:" + usuario.getIdUsuario() + ", monopatin:" + monopatin.getPatente()
				+ ", fechaInicio:" + fechaInicio + ", fechaFin:" + fechaFin + ", kmRecorridos:" 
				+ ", estadoPausa:" + estadoPausa + ", precioFinal:" + precioFinal + ", precioEstimado:" + precioEstimado
				+ ", precioRecorridoActual:" + precioRecorridoActual + ", tiempoPausa:" + tiempoPausa + "]";
	}

}