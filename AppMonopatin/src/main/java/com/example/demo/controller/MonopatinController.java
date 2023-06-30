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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.security.JWTAuthorizationFilter;

import com.example.demo.dtos.MonopatinEstadoDto;
import com.example.demo.dtos.ReporteMonopatinDto;
import com.example.demo.model.Monopatin;
import com.example.demo.model.Parada;
import com.example.demo.services.MonopatinServicio;
import com.example.demo.services.ParadaServicio;

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
	public ResponseEntity<Monopatin> agregarMonopatin(@RequestBody Monopatin monopatin,
			@RequestHeader("Authorization") String token) {
		if (JWTAuthorizationFilter.verificarTokenContieneAutorizacion(token, "ROLE_ADMIN")) {
			if (monopatinServicio.save(monopatin) != null)
				return new ResponseEntity<>(monopatin, HttpStatus.CREATED);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@GetMapping("/obtener/{patente}")
	public Monopatin findByPatente(@PathVariable String patente) {
		return monopatinServicio.findByPatente(patente);
	}

	@DeleteMapping(value = "/borrar/{id}")
	public ResponseEntity<String> borrar(@PathVariable int id, @RequestHeader("Authorization") String token) {
		if (JWTAuthorizationFilter.verificarTokenContieneAutorizacion(token, "ROLE_ADMIN")) {
			if (monopatinServicio.delete(id))
				return new ResponseEntity<>("Borrado id: " + id, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@PutMapping("/mantenimiento")
	public ResponseEntity<Monopatin> actualizarMantenimiento(@RequestBody MonopatinEstadoDto mmdto,
			@RequestHeader("Authorization") String token) {
		if (JWTAuthorizationFilter.verificarTokenContieneAutorizacion(token, "ROLE_ADMIN")) {
			Monopatin monopatin = monopatinServicio.actualizarEstadoMantenimiento(mmdto);
			if (monopatin != null)
				return new ResponseEntity<>(monopatin, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

	}
	
	

	@PutMapping("/enuso")
	public ResponseEntity<Monopatin> actualizarEstadoEnUso(@RequestBody MonopatinEstadoDto mmdto,
			@RequestHeader("Authorization") String token) {
		if (JWTAuthorizationFilter.verificarTokenContieneAutorizacion(token, "ROLE_ADMIN")) {
			Monopatin monopatin = monopatinServicio.actualizarEstadoEnUso(mmdto);
			if (monopatin != null)
				return new ResponseEntity<>(monopatin, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@GetMapping("/reporte/conpausa")
	public ResponseEntity<List<ReporteMonopatinDto>> obtenerReporteConPausa(
			@RequestHeader("Authorization") String token) {
		if (JWTAuthorizationFilter.verificarTokenContieneAutorizacion(token, "ROLE_ADMIN")) {
			return new ResponseEntity<>(monopatinServicio.obtenerReporteConPausa(), HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

	}

	@GetMapping("/reporte/sinpausa")
	public ResponseEntity<List<ReporteMonopatinDto>> obtenerReporteSinPausa(
			@RequestHeader("Authorization") String token) {
		if (JWTAuthorizationFilter.verificarTokenContieneAutorizacion(token, "ROLE_ADMIN")) {
			return new ResponseEntity<>(monopatinServicio.obtenerReporteSinPausa(), HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@GetMapping("/reporte/kilometros")
	public ResponseEntity<List<ReporteMonopatinDto>> obtenerReportePorKilometraje(
			@RequestHeader("Authorization") String token) {
		if (JWTAuthorizationFilter.verificarTokenContieneAutorizacion(token, "ROLE_ADMIN")) {
			return new ResponseEntity<>(monopatinServicio.obtenerReportePorKilometraje(), HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
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