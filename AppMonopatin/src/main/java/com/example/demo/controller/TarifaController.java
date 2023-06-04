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

import com.example.demo.model.Tarifa;
import com.example.demo.services.TarifaServicio;

@RestController
@RequestMapping("tarifas")
public class TarifaController {

	@Qualifier("tarifaServicio")
	@Autowired

	private TarifaServicio tarifaServicio;

	public TarifaController(@Qualifier("tarifaServicio") TarifaServicio tarifa) {
		this.tarifaServicio = tarifa;
	}

	@GetMapping("/historico")
	public List<Tarifa> getHistoricoTarifas() {
		return tarifaServicio.findAll();
	}

	@PostMapping(value = "/agregar", headers = "content-type=application/json")
	public Tarifa agregar(@RequestBody Tarifa tarifa) {
		return tarifaServicio.save(tarifa);
	}
	
	@GetMapping(value = "/ultima")
	public Tarifa obtenerUltimaTarifa() {
		return tarifaServicio.findTopByOrderByIdTarifaDesc();
	}

	@GetMapping(value = "/valorregular")
	public float obtenerTarifaRegular() {
		return (float) tarifaServicio.findTopByOrderByIdTarifaDesc().getTarifaRegular();
	}

	@GetMapping(value = "/valorpausa")
	public float obtenerTarifaPausa() {
		return (float) tarifaServicio.findTopByOrderByIdTarifaDesc().getTarifaPausa();
	}

	@DeleteMapping(value = "/borrar/{id}")
	public ResponseEntity<String> borrar(@PathVariable int id) {
		if (tarifaServicio.delete(id))
			return new ResponseEntity<>("Borrado id: " + id, HttpStatus.OK);
		else
			return new ResponseEntity<>("No borrado", HttpStatus.BAD_REQUEST);
	}
}
