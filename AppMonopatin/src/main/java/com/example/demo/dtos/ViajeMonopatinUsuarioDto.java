package com.example.demo.dtos;

public class ViajeMonopatinUsuarioDto {

	public int idMonopatin;

	public int idUsuario;
	
	public long precioEstimado;

	public ViajeMonopatinUsuarioDto() {
	}

	public ViajeMonopatinUsuarioDto(int idMonopatin, int idUsuario, long precioEstimado) {
		this.idMonopatin = idMonopatin;
		this.idUsuario = idUsuario;
		this.precioEstimado = precioEstimado;
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

	public long getPrecioEstimado() {
		return precioEstimado;
	}

	public void setPrecioEstimado(long precioEstimado) {
		this.precioEstimado = precioEstimado;
	}

	
}
