package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Administrador;
import com.example.demo.services.AdministradorServicio;

@SpringBootTest
class TestServicios {
	
	AdministradorServicio administradorServicio;
	
	TestServicios(AdministradorServicio administradorServicio){
		this.administradorServicio = administradorServicio;
	}
	
	@Test
	Administrador TestBuscarAdministradorPorId(int id){
		return administradorServicio.findById(id);
	}

}
