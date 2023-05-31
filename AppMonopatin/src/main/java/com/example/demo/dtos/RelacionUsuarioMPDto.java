package com.example.demo.dtos;

public class RelacionUsuarioMPDto {

	private int idUsuario;

	private int idCuentaMP;

	public RelacionUsuarioMPDto(int idUsuario, int idCuentaMP) {
		this.idUsuario = idUsuario;
		this.idCuentaMP = idCuentaMP;
	}

	public RelacionUsuarioMPDto() {
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdCuentaMP() {
		return idCuentaMP;
	}

	public void setIdCuentaMP(int idCuentaMP) {
		this.idCuentaMP = idCuentaMP;
	}

}
