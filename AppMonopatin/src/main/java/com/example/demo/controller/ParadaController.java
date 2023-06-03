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

import com.example.demo.dtos.ParadaMonopatinDto;
import com.example.demo.model.Parada;
import com.example.demo.services.MonopatinServicio;
import com.example.demo.services.ParadaServicio;

@RestController
@RequestMapping("paradas")
public class ParadaController {

	@Qualifier("paradaServicio")
	@Autowired

	private ParadaServicio paradaServicio;

	public ParadaController(@Qualifier("paradaServicio") ParadaServicio paradaServicio) {
		this.paradaServicio = paradaServicio;
	}

	@GetMapping("/")
	public List<Parada> getParadas() {
		return paradaServicio.findAll();
	}

	@PostMapping(value = "/agregar", headers = "content-type=application/json")
	public Parada agregar(@RequestBody Parada parada) {
		return paradaServicio.save(parada);
	}

	@PostMapping(value = "/agregarmonopatin")
	public ResponseEntity<Parada> agregarRelacionMonopatin(@RequestBody ParadaMonopatinDto relacion) {
		Parada parada = paradaServicio.agregarRelacionMonopatin(relacion);
		if (parada != null)
			return new ResponseEntity<>(parada, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(value = "/borrar/{id}")
	public ResponseEntity<String> borrar(@PathVariable int id) {
		if (paradaServicio.delete(id)) {
			return new ResponseEntity<>("Borrado", HttpStatus.OK);
		} else
			return new ResponseEntity<>("No borrado", HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = "/cercanas/{latitud}/{longitud}/{distancia}")
	public List<Parada> obtenerCercanas(@PathVariable Double latitud, @PathVariable Double longitud,
			@PathVariable double distancia) {
		return paradaServicio.obtenerParadasCercanas(longitud, latitud, distancia);
	}

	// https://stackoverflow.com/questions/27379233/android-find-nearest-location-to-my-current-place
}