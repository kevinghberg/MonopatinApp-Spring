package com.example.demo.model;

import java.time.LocalDate;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Viaje {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idViaje;
	
	@Column
	private Monopatin monopatin;
	
	@Column
	private Usuario usuario;
	
	@Column
	private LocalDate fechaInicio;
	
	@Column
	private LocalDate fechaFin;
	
	@Column
	private int kmRecorridos;
	
	@Column
	private boolean estadoPausa;
	
	@Column
	private String precioFinal;
	
	@Column
	private String precioEstimado;
	
	@Column
	private String precioRecorridoActual;
	
	@Column
	private int tiempoPausa;

	
	
	public Viaje() {
		super();
	}

	public Viaje(Monopatin monopatin, Usuario usuario, LocalDate fechaInicio, LocalDate fechaFin, int kmRecorridos,
			boolean estadoPausa, String precioFinal, String precioEstimado, String precioRecorridoActual,
			int tiempoPausa) {
		super();
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
	
	

	
	
	

}
