package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Tarifa;
import com.example.demo.repository.TarifaRepository;

@RestController
@RequestMapping("tarifas")
public class TarifaController {

	@Qualifier("tarifaRepository")
	@Autowired

	private TarifaRepository repository;

	public TarifaController(@Qualifier("tarifaRepository") TarifaRepository tarifa) {
		this.repository = tarifa;
	}

	@GetMapping("/historico/")
	public List<Tarifa> getHistoricoTarifas() {
		return repository.findAll();
	}

	@PostMapping(value = "/agregar/", headers = "content-type=application/json")
	public Tarifa agregar(@RequestBody Tarifa tarifa) {
		return repository.save(tarifa);
	}
}
