package com.example.demo.dtos;

public class ViajeMonopatinUsuarioDto {

	public int idMonopatin;

	public int idUsuario;

	public int idParadaComienzo;
	public int idParadaDestino;

	public ViajeMonopatinUsuarioDto() {
	}

	public ViajeMonopatinUsuarioDto(int idMonopatin, int idUsuario,int idParadaComienzo, int idParadaDestino) {
		this.idMonopatin = idMonopatin;
		this.idUsuario = idUsuario;
		this.idParadaComienzo = idParadaComienzo;
		this.idParadaDestino = idParadaDestino;
	}

	public int getIdMonopatin() {
		return idMonopatin;
	}

	public void setIdMonopatin(int idMonopatin) {
		this.idMonopatin = idMonopatin;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

}
