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
	
	@Column
	private float latitud;
	
	@Column
	private float longitud;
	
	public Parada() {
		super();
	}

	public Parada(float latitud, float longitud) {
		super();
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


	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

}
