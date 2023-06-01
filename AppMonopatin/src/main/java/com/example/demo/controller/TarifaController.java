package com.example.demo.controller;

import java.util.Collections;
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

import com.example.demo.model.Parada;
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

	@GetMapping("/historico")
	public List<Tarifa> getHistoricoTarifas() {
		return repository.findAll();
	}

	@PostMapping(value = "/agregar", headers = "content-type=application/json")
	public Tarifa agregar(@RequestBody Tarifa tarifa) {
		return repository.save(tarifa);
	}

	@GetMapping(value = "/regular")
	public long obtenerTarifaRegular() {
		List<Tarifa> tarifas = repository.findAll();
		Collections.sort(tarifas, (a, b) -> a.getFecha().compareTo(b.getFecha()));
		return tarifas.get(0).getTarifaRegular();
	}

	@GetMapping(value = "/pausa")
	public long obtenerTarifaPausa() {
		List<Tarifa> tarifas = repository.findAll();
		Collections.sort(tarifas, (a, b) -> a.getFecha().compareTo(b.getFecha()));
		return tarifas.get(0).getTarifaPausa();
	}
	
	@DeleteMapping(value = "/borrar/{id}")
	public ResponseEntity<Tarifa> borrar(@PathVariable int id) {
		Tarifa tarifa = repository.findById(id);
		System.out.println(tarifa);
		if (tarifa != null) {
			repository.delete(tarifa);
			return new ResponseEntity<>(tarifa, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
