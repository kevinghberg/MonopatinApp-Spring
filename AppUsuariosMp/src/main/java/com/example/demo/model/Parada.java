package com.example.demo.model;

import java.util.Set;

public class Parada {

	private int id;
	private Set<Monopatin> listaMonopatin;
    private Viaje viaje;
	private double latitud;
	private double longitud;
	
	public Parada() {
	}

	public Parada(double d, double e) {
		this.latitud = d;
		this.longitud = e;
	}

	public int getIdParada() {
		return id;
	}

	public void setIdParada(int id) {
		this.id = id;
	}

	public Set<Monopatin> getListaMonopatines() {
		return listaMonopatin;
	}

	public void setListaMonopatines(Set<Monopatin> listaMonopatin) {
		this.listaMonopatin = listaMonopatin;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public Viaje getViaje() {
		return viaje;
	}

	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}

}
