package com.example.demo.model;

import java.util.Set;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Parada {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "monopatin_id")
	private Set<Monopatin> listaMonopatin;
    
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "viaje")
    private Viaje viaje;
	
	@Column
	private double latitud;
	
	@Column
	private double longitud;
	
	public Parada() {
	}

	public Parada(float latitud, float longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
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

}
