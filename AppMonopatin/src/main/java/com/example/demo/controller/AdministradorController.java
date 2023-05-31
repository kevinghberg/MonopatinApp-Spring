package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
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
	public List<Administrador> getCuentasMP() {
		return repository.findAll();
	}

	@PostMapping(value = "/agregar/", headers = "content-type=application/json")
	public Administrador agregar(@RequestBody Administrador admin) {
		return repository.save(admin);
	}
}