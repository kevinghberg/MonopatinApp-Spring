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

	@Column
	private LocalDate fechaInicio;

	@Column
	private LocalDate fechaFin;

	@Column
	private int kmRecorridos;

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

	public Viaje(Monopatin monopatin, Usuario usuario, LocalDate fechaInicio, LocalDate fechaFin, int kmRecorridos,
			boolean estadoPausa, long precioFinal, long precioEstimado, long precioRecorridoActual, int tiempoPausa) {
		this.monopatin = monopatin;
		this.usuario = usuario;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.kmRecorridos = kmRecorridos;
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

	public int getKmRecorridos() {
		return kmRecorridos;
	}

	public void setKmRecorridos(int kmRecorridos) {
		this.kmRecorridos = kmRecorridos;
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

	public void setPrecioFinal(long precioFinal) {
		this.precioFinal = precioFinal;
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

	@Override
	public String toString() {
		return "Viaje [id:" + idViaje + ", usuario:" + usuario.getIdUsuario() + ", monopatin:" + monopatin.getPatente()
				+ ", fechaInicio:" + fechaInicio + ", fechaFin:" + fechaFin + ", kmRecorridos:" + kmRecorridos
				+ ", estadoPausa:" + estadoPausa + ", precioFinal:" + precioFinal + ", precioEstimado:" + precioEstimado
				+ ", precioRecorridoActual:" + precioRecorridoActual + ", tiempoPausa:" + tiempoPausa + "]";
	}

}