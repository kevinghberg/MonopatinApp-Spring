package com.example.demo.model;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "monopatin")
public class Monopatin {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMonopatin;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "monopatin")
	private Viaje viaje;

	@ManyToOne(fetch = FetchType.LAZY)
	private Parada parada;

	@Column
	private boolean enUso;

	@Column
	private boolean estadoMantenimiento;

	@Column
	private double tiempoUso;

	@Column
	private double kilometrosRecorridos;

	@Column(unique = true)
	private String patente;

	public Monopatin() {
	}

	public Monopatin(String patente) {
		this.patente = patente;
		this.enUso = false;
		this.estadoMantenimiento = false;
		this.tiempoUso = 0;
		this.kilometrosRecorridos = 0;
	}

	public int getIdMonopatin() {
		return idMonopatin;
	}

	public void setIdMonopatin(int idMonopatin) {
		this.idMonopatin = idMonopatin;
	}

	public boolean isEnUso() {
		return enUso;
	}

	public void setEnUso(boolean enUso) {
		this.enUso = enUso;
	}

	public boolean isEstadoMantenimiento() {
		return estadoMantenimiento;
	}

	public void setEstadoMantenimiento(boolean estadoMantenimiento) {
		this.estadoMantenimiento = estadoMantenimiento;
	}

	public double getTiempoUso() {
		return tiempoUso;
	}

	public void setTiempoUso(double tiempoUso) {
		this.tiempoUso = tiempoUso;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public double getKilometrosRecorridos() {
		return kilometrosRecorridos;
	}

	public void setKilometrosRecorridos(double kilometrosRecorridos) {
		this.kilometrosRecorridos = kilometrosRecorridos;
	}

}
