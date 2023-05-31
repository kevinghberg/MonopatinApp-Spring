package com.example.demo.model;

import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Parada {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idParada;
	
	/*@Column
	private List<Monopatin> listaMonopatines;*/
	
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

}
