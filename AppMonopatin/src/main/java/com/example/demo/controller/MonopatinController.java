package com.example.demo.controller;

import java.util.List;
import java.util.ArrayList;

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

import com.example.demo.dtos.MonopatinEstadoDto;
import com.example.demo.dtos.ReporteMonopatinDto;
import com.example.demo.model.Monopatin;
import com.example.demo.model.Parada;
import com.example.demo.services.MonopatinServicio;
import com.example.demo.services.ParadaServicio;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import io.swagger.annotations.ApiModelProperty;

@RestController
@RequestMapping("monopatines")
public class MonopatinController {

	@Qualifier("monopatinServicio")
	@Autowired

	private MonopatinServicio monopatinServicio;
	private ParadaServicio paradaServicio;

	public MonopatinController(@Qualifier("monopatinServicio") MonopatinServicio monopatin,
			@Qualifier("paradaServicio") ParadaServicio paradaServicio) {
		this.paradaServicio = paradaServicio;
		this.monopatinServicio = monopatin;
	}

	@GetMapping("/")
	public List<Monopatin> getMonopatines() {
		return monopatinServicio.findAll();
	}

	@PostMapping(value = "/agregar", headers = "content-type=application/json")
	public Monopatin agregarMonopatin(@RequestBody Monopatin monopatin) {
		return monopatinServicio.save(monopatin);
	}

	@GetMapping("/obtener/{patente}")
	public Monopatin findByPatente(@PathVariable String patente) {
		return monopatinServicio.findByPatente(patente);
	}

	@DeleteMapping(value = "/borrar/{id}")
	public ResponseEntity<String> borrar(@PathVariable int id) {
		if (monopatinServicio.delete(id))
			return new ResponseEntity<>("Borrado id: " + id, HttpStatus.OK);
		else
			return new ResponseEntity<>("No borrado", HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/mantenimiento")
	public ResponseEntity<Monopatin> actualizarMantenimiento(@RequestBody MonopatinEstadoDto mmdto) {
		Monopatin monopatin = monopatinServicio.actualizarEstadoMantenimiento(mmdto);
		if (monopatin != null)
			return new ResponseEntity<>(monopatin, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/enuso")
	public ResponseEntity<Monopatin> actualizarEstadoEnUso(@RequestBody MonopatinEstadoDto mmdto) {
		Monopatin monopatin = monopatinServicio.actualizarEstadoEnUso(mmdto);
		if (monopatin != null)
			return new ResponseEntity<>(monopatin, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/reporte/conpausa")
	public List<ReporteMonopatinDto> obtenerReporteConPausa() {
		return monopatinServicio.obtenerReporteConPausa();
	}

	@GetMapping("/reporte/sinpausa")
	public List<ReporteMonopatinDto> obtenerReporteSinPausa() {
		return monopatinServicio.obtenerReporteSinPausa();
	}

	@GetMapping("/reporte/kilometros")
	public List<ReporteMonopatinDto> obtenerReportePorKilometraje() {
		return monopatinServicio.obtenerReportePorKilometraje();
	}

	@GetMapping(value = "/buscarpordistancia/{latitud}/{longitud}/{distancia}")
	public List<Monopatin> obtenerCercanas(@PathVariable Double latitud, @PathVariable Double longitud,
			@PathVariable double distancia) {
		List<Parada> listaParadas = paradaServicio.obtenerParadasCercanas(longitud, latitud, distancia);
		List<Monopatin> listaMonopatines = new ArrayList<Monopatin>();
		for (Parada parada : listaParadas) {
			for (Monopatin monopatin : parada.getListaMonopatines()) {
				if (!monopatin.isEnUso() && !monopatin.isEstadoMantenimiento()) {
					listaMonopatines.add(monopatin);
				}
			}
		}
		return listaMonopatines;
	}

}