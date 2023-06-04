package com.example.demo.model;

import javax.persistence.*;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Data
@Table(name = "monopatin")
public class Monopatin {

	@Id
	@Column(name = "id")
	@ApiModelProperty(hidden = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMonopatin;

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "monopatin")
	@ApiModelProperty(hidden = true)
	private Viaje viaje;

	@ManyToOne(fetch = FetchType.EAGER)
	@ApiModelProperty(hidden = true)
	private Parada parada;

	@Column
	@ApiModelProperty(hidden = true)
	private boolean enUso;

	@Column
	@ApiModelProperty(hidden = true)
	private boolean estadoMantenimiento;

	@Column
	@ApiModelProperty(hidden = true)
	private double tiempoUso;

	@Column
	@ApiModelProperty(hidden = true)
	private double kilometrosRecorridos;

	@Column
	@ApiModelProperty(hidden = true)
	private int tiempoPausaTotal;

	@Column
	@ApiModelProperty(hidden = true)
	private int cantidadViajes;

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

	public int getTiempoPausaTotal() {
		return tiempoPausaTotal;
	}

	public void setTiempoPausaTotal(int tiempoPausaTotal) {
		this.tiempoPausaTotal = tiempoPausaTotal;
	}

	public int getCantidadViajes() {
		return cantidadViajes;
	}

	public void setCantidadViajes(int cantidadViajes) {
		this.cantidadViajes = cantidadViajes;
	}

}
