package com.example.demo.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dtos.ViajeMonopatinUsuarioDto;
import com.example.demo.model.Viaje;
import com.example.demo.services.*;

@RestController
@RequestMapping("viajes")
public class ViajeController {

	@Qualifier("viajeServicio")
	@Autowired

	private ViajeServicio viajeServicio;
	private MonopatinServicio monopatinServicio;
	private UsuarioServicio usuarioServicio;

	public ViajeController(@Qualifier("viajeServicio") ViajeServicio viajeServicio,
			@Qualifier("monopatinServicio") MonopatinServicio monopatinServicio,
			@Qualifier("usuarioServicio") UsuarioServicio usuarioServicio) {
		this.viajeServicio = viajeServicio;
		this.monopatinServicio = monopatinServicio;
		this.usuarioServicio = usuarioServicio;
	}

	@GetMapping("/")
	public List<Viaje> getViajes() {
		return viajeServicio.getViajes();

	}

	@PostMapping(value = "/agregar", headers = "content-type=application/json")
	public Viaje agregarViaje(@RequestBody Viaje viaje) {
		return viajeServicio.agregarViaje(viaje);
	}

	@PostMapping(value = "/reservarmonopatin", headers = "content-type=application/json")
	public ResponseEntity<String> reservarMonopatin(@RequestBody ViajeMonopatinUsuarioDto vmudto) {
		Viaje viaje = viajeServicio.reservarMonopatin(vmudto);
		if (viaje != null) {
			return new ResponseEntity<>(viaje.toString(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No reservado",HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/borrar/{id}")
	public ResponseEntity<String> borrar(@PathVariable int id) {
		if (viajeServicio.borrarViaje(id)) {
			return new ResponseEntity<String>("Borrado", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No borrado", HttpStatus.BAD_REQUEST);
		}

	}
}
