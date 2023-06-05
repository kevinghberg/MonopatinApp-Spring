package com.example.demo.dtos;

public class ReporteViajeIdCountMonopatinDto {

	private String patente;
	private long count;

	public ReporteViajeIdCountMonopatinDto(String patente, long count) {
		this.patente = patente;
		this.count = count;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

}
