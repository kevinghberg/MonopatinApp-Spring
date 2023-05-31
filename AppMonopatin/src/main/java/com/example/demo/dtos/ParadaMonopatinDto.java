package com.example.demo.dtos;

public class ParadaMonopatinDto {

	private int idMonopatin;
	private int idParada;

	public ParadaMonopatinDto() {
	}

	public ParadaMonopatinDto(int idMonopatin, int idParada) {
		this.idMonopatin = idMonopatin;
		this.idParada = idParada;
	}

	public int getIdMonopatin() {
		return idMonopatin;
	}

	public void setIdMonopatin(int idMonopatin) {
		this.idMonopatin = idMonopatin;
	}

	public int getIdParada() {
		return idParada;
	}

	public void setIdParada(int idParada) {
		this.idParada = idParada;
	}

}
