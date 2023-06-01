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

@RestController
@RequestMapping("administrador")
public class AdministradorController {

	@Qualifier("administradorRepository")
	@Autowired

	private AdministradorRepository repository;

	public AdministradorController(@Qualifier("administradorRepository") AdministradorRepository admin) {
		this.repository = admin;
	}

	@GetMapping("/")
	public List<Administrador> getListaAdmins() {
		return repository.findAll();
	}

	@PostMapping(value = "/agregar", headers = "content-type=application/json")
	public Administrador agregar(@RequestBody Administrador admin) {
		return repository.save(admin);
	}

	@DeleteMapping(value = "/borrar/{id}")
	public ResponseEntity<Administrador> borrar(@PathVariable int id) {
		Administrador administrador = repository.findById(id);
		System.out.println(administrador);
		if (administrador != null) {
			repository.delete(administrador);
			return new ResponseEntity<>(administrador, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
