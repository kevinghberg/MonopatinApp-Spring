package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.ViajeMonopatinUsuarioDto;
import com.example.demo.model.Monopatin;
import com.example.demo.model.Usuario;
import com.example.demo.model.Viaje;
import com.example.demo.repository.MonopatinRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.repository.ViajeRepository;

@RestController
@RequestMapping("viajes")
public class ViajeController {

	@Qualifier("viajeRepository")
	@Autowired

	private ViajeRepository repository;
	private MonopatinRepository monopatinRepository;
	private UsuarioRepository usuarioRepository;

	public ViajeController(@Qualifier("viajeRepository") ViajeRepository viaje,
			@Qualifier("monopatinRepository") MonopatinRepository monopatinRepository,
			@Qualifier("usuarioRepository") UsuarioRepository usuarioRepository) {
		this.repository = viaje;
		this.monopatinRepository = monopatinRepository;
		this.usuarioRepository = usuarioRepository;
	}

	@GetMapping("/")
	public List<Viaje> getUsuarios() {
		return repository.findAll();
	}

	@PostMapping(value = "/agregar", headers = "content-type=application/json")
	public Viaje agregarViaje(@RequestBody Viaje viaje) {
		return repository.save(viaje);
	}

	@PostMapping(value = "/reservarmonopatin", headers = "content-type=application/json")
	public ResponseEntity<Viaje> reservarMonopatin(@RequestBody ViajeMonopatinUsuarioDto vmu) {
		Viaje viaje = new Viaje();
		Usuario usuario = usuarioRepository.findByIdUsuario(vmu.getIdUsuario());
		Monopatin monopatin = monopatinRepository.findByIdMonopatin(vmu.getIdMonopatin());
		if (usuario == null && monopatin == null) {
			long tarifa = vmu.getPrecioEstimado();
			viaje.setMonopatin(monopatin);
			viaje.setUsuario(usuario);
			viaje.setPrecioEstimado(tarifa);
			return new ResponseEntity<>(repository.save(viaje), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
