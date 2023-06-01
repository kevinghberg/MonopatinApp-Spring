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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.ParadaMonopatinDto;
import com.example.demo.model.Localidad;
import com.example.demo.model.Monopatin;
import com.example.demo.model.Parada;
import com.example.demo.repository.MonopatinRepository;
import com.example.demo.repository.ParadaRepository;
import com.example.demo.services.MonopatinServicio;
import com.example.demo.services.ParadaServicio;

@RestController
@RequestMapping("paradas")
public class ParadaController {

	@Qualifier("paradaServicio")
	@Autowired

	private ParadaServicio paradaServicio;
	private MonopatinServicio monopatinServicio;

	public ParadaController(@Qualifier("paradaServicio") ParadaServicio paradaServicio,
			@Qualifier("monopatinServicio") MonopatinServicio monopatinServicio) {
		this.paradaServicio = paradaServicio;
		this.monopatinServicio = monopatinServicio;
	}

	@GetMapping("/")
	public List<Parada> getParadas() {
		return paradaServicio.findAll();
	}

	@PostMapping(value = "/agregar", headers = "content-type=application/json")
	public Parada agregar(@RequestBody Parada parada) {
		return paradaServicio.save(parada);
	}

	@PostMapping(value = "/actualizar")
	public Parada agregarMonopatin(@RequestBody ParadaMonopatinDto relacion) {
		Parada parada = paradaServicio.findById(relacion.getIdParada());
		parada.getListaMonopatines().add(monopatinServicio.findByIdMonopatin(relacion.getIdMonopatin()));
		return paradaServicio.save(parada);
	}

	@DeleteMapping(value = "/borrar/{id}")
	public ResponseEntity<Parada> borrar(@PathVariable int id) {
		Parada parada = paradaServicio.findById(id);
		System.out.println(parada);
		if (parada != null) {
			paradaServicio.delete(parada);
			return new ResponseEntity<>(parada, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = "/diferencia/{latitud}/{longitud}/{distancia}")
	public List<Parada> obtenerCercanas(@PathVariable Double latitud, @PathVariable Double longitud, @PathVariable double distancia) {
		List<Parada> listaCercanas = paradaServicio.obtenerParadasCercanas(longitud, latitud, distancia);
		System.out.println(listaCercanas);
		return listaCercanas;
	}

	// https://stackoverflow.com/questions/27379233/android-find-nearest-location-to-my-current-place
}