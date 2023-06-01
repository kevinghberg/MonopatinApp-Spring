package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Administrador;
import com.example.demo.repository.AdministradorRepository;
import com.example.demo.services.AdministradorServicio;

@RestController
@RequestMapping("administrador")
public class AdministradorController {

	@Qualifier("administradorServicio")
	@Autowired

	private AdministradorServicio administradorServicio;

	public AdministradorController(@Qualifier("administradorServicio") AdministradorServicio administradorServicio) {
		this.administradorServicio = administradorServicio;
	}

	@GetMapping("/")
	public List<Administrador> getListaAdmins() {
		return administradorServicio.obtenerListaAdmins();
	}

	@PostMapping(value = "/agregar", headers = "content-type=application/json")
	public Administrador agregar(@RequestBody Administrador admin) {
		return administradorServicio.agregarAdmin(admin);
	}

	@DeleteMapping(value = "/borrar/{id}")
	public ResponseEntity<String> borrar(@PathVariable int id) {
		if (administradorServicio.borrar(id)) {
			return new ResponseEntity<>("Borrado", HttpStatus.OK);
		} else
			return new ResponseEntity<>("No borrado", HttpStatus.BAD_REQUEST);
	}
}
