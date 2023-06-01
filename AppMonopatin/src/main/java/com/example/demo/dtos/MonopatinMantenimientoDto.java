package com.example.demo.dtos;

public class MonopatinMantenimientoDto {

	private int estadoMantenimiento;
	private int id;

	public MonopatinMantenimientoDto() {

	}

	public MonopatinMantenimientoDto(int estadoMantenimiento, int id) {
		super();
		this.estadoMantenimiento = estadoMantenimiento;
		this.id = id;
	}

	public int getEstadoMantenimiento() {
		return estadoMantenimiento;
	}

	public void setEstadoMantenimiento(int estadoMantenimiento) {
		this.estadoMantenimiento = estadoMantenimiento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
