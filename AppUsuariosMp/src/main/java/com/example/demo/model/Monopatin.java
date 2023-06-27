package com.example.demo.model;

public class Monopatin {

	private int idMonopatin;

	private Viaje viaje;

	private Parada parada;

	private boolean enUso;

	private boolean estadoMantenimiento;

	private double tiempoUso;

	private double kilometrosRecorridos;

	private int tiempoPausaTotal;

	private int cantidadViajes;

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
	
	public Viaje getViaje() {
		return viaje;
	}

	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}

	public Parada getParada() {
		return parada;
	}

	public void setParada(Parada parada) {
		this.parada = parada;
	}

}
