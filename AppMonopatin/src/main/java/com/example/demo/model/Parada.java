package com.example.demo.model;

import java.util.Set;

import javax.persistence.*;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Data
@Table(name = "parada")
public class Parada {
	
	@Id
    @Column(name = "id")
	@ApiModelProperty(hidden = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "monopatin_id")
	@ApiModelProperty(hidden = true)
	private Set<Monopatin> listaMonopatin;
    
    @OneToOne(fetch = FetchType.EAGER)
	@ApiModelProperty(hidden = true)
    private Viaje viaje;
    
	@Column
	private double latitud;
	
	@Column
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

}
