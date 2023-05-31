package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Parada;
import com.example.demo.repository.ParadaRepository;

@RestController
@RequestMapping("paradas")
public class ParadaController {

	@Qualifier("paradaRepository")
	@Autowired

	private ParadaRepository repository;

	public ParadaController(@Qualifier("paradaRepository") ParadaRepository parada) {
		this.repository = parada;
	}

	@GetMapping("/")
	public List<Parada> getParadas() {
		return repository.findAll();
	}

	@PostMapping(value = "/agregar/", headers = "content-type=application/json")
	public Parada agregar(@RequestBody Parada parada) {
		return repository.save(parada);
	}
}