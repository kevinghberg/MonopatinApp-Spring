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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Tarifa;
import com.example.demo.security.JWTAuthorizationFilter;
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
	public ResponseEntity<Tarifa> agregar(@RequestBody Tarifa tarifa, @RequestHeader("Authorization") String token) {
		if (JWTAuthorizationFilter.verificarTokenContieneAutorizacion(token, "ROLE_ADMIN"))
			if (tarifaServicio.save(tarifa) != null)
				return new ResponseEntity<>(tarifa, HttpStatus.CREATED);
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
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
	public ResponseEntity<String> borrar(@PathVariable int id, @RequestHeader("Authorization") String token) {
		if (JWTAuthorizationFilter.verificarTokenContieneAutorizacion(token, "ROLE_ADMIN"))
			if (tarifaServicio.delete(id))
				return new ResponseEntity<>("Borrado id: " + id, HttpStatus.OK);
			else
				return new ResponseEntity<>("No borrado", HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
}
