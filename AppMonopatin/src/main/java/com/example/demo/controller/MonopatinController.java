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

import com.example.demo.dtos.MonopatinMantenimientoDto;
import com.example.demo.model.Monopatin;
import com.example.demo.repository.MonopatinRepository;

@RestController
@RequestMapping("monopatines")
public class MonopatinController {

	@Qualifier("monopatinRepository")
	@Autowired

	private MonopatinRepository repository;

	public MonopatinController(@Qualifier("monopatinRepository") MonopatinRepository monopatin) {
		this.repository = monopatin;
	}

	@GetMapping("/")
	public List<Monopatin> getMonopatines() {
		return repository.findAll();
	}

	@PostMapping(value = "/agregar", headers = "content-type=application/json")
	public Monopatin agregarMonopatin(@RequestBody Monopatin monopatin) {
		return repository.save(monopatin);
	}

	@GetMapping("/obtener/{patente}")
	public Monopatin findByPatente(@PathVariable String patente) {
		return repository.findByPatente(patente);
	}

	@DeleteMapping(value = "/borrar/{id}")
	public ResponseEntity<Monopatin> borrar(@PathVariable int id) {
		Monopatin monopatin = repository.findByIdMonopatin(id);
		System.out.println(monopatin);
		if (monopatin != null) {
			repository.delete(monopatin);
			return new ResponseEntity<>(monopatin, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/mantenimiento")
	public ResponseEntity<Monopatin> actualizarMantenimiento(@RequestBody MonopatinMantenimientoDto mmdto) {
		Monopatin monopatin = repository.findByIdMonopatin(mmdto.getId());
		if (monopatin != null) {
			if (mmdto.getEstadoMantenimiento() == 1)
				monopatin.setEstadoMantenimiento(true);
			else
				monopatin.setEstadoMantenimiento(false);
			return new ResponseEntity<>(repository.save(monopatin), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}