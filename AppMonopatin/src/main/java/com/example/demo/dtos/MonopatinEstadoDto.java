package com.example.demo.dtos;

public class MonopatinEstadoDto {

	private int estado;
	private int id;

	public MonopatinEstadoDto() {

	}

	public MonopatinEstadoDto(int estado, int id) {
		super();
		this.estado = estado;
		this.id = id;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estadoMantenimiento) {
		this.estado = estadoMantenimiento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
