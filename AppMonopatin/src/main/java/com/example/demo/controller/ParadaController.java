package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.ParadaMonopatinDto;
import com.example.demo.model.Monopatin;
import com.example.demo.model.Parada;
import com.example.demo.repository.MonopatinRepository;
import com.example.demo.repository.ParadaRepository;

@RestController
@RequestMapping("paradas")
public class ParadaController {

	@Qualifier("paradaRepository")
	@Autowired

	private ParadaRepository repository;
	private MonopatinRepository monopatinRepository;

	public ParadaController(@Qualifier("paradaRepository") ParadaRepository parada,
			@Qualifier("monopatinRepository") MonopatinRepository monopatin) {
		this.repository = parada;
		this.monopatinRepository = monopatin;
	}

	@GetMapping("/")
	public List<Parada> getParadas() {
		return repository.findAll();
	}

	@PostMapping(value = "/agregar", headers = "content-type=application/json")
	public Parada agregar(@RequestBody Parada parada) {
		return repository.save(parada);
	}

	@PostMapping(value = "/actualizar")
	public Parada agregarMonopatin(@RequestBody ParadaMonopatinDto relacion) {
		Parada parada = repository.findById(relacion.getIdParada());
		parada.getListaMonopatines().add(monopatinRepository.findByIdMonopatin(relacion.getIdMonopatin()));
		return repository.save(parada);
	}
}