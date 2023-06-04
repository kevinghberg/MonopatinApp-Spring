package com.example.demo.dtos;

public class ReporteMonopatinDto {

	private int id;
	private double valor;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	private String patente;

	public ReporteMonopatinDto() {

	}

	public ReporteMonopatinDto(int id, double valor, String patente) {
		this.id = id;
		this.valor = valor;
		this.patente = patente;
	}

}
