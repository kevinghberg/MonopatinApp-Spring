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

import com.example.demo.model.Administrador;
import com.example.demo.security.JWTAuthorizationFilter;
import com.example.demo.services.AdministradorServicio;

@RestController
@RequestMapping("administrador")
public class AdministradorController {

	@Qualifier("administradorServicio")
	@Autowired

	private AdministradorServicio administradorServicio;

	public AdministradorController(@Qualifier("administradorServicio") AdministradorServicio administradorServicio) {
		this.administradorServicio = administradorServicio;
	}

	@GetMapping("/")
	public ResponseEntity<List<Administrador>> getListaAdministradores(@RequestHeader("Authorization") String token) {
		if (JWTAuthorizationFilter.verificarTokenContieneAutorizacion(token, "ROLE_ADMIN"))
			return new ResponseEntity<>(administradorServicio.findAll(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@PostMapping(value = "/agregar", headers = "content-type=application/json")
	public ResponseEntity<Administrador> agregar(@RequestBody Administrador admin,
			@RequestHeader("Authorization") String token) {
		if (JWTAuthorizationFilter.verificarTokenContieneAutorizacion(token, "ROLE_ADMIN"))
			if (administradorServicio.save(admin) != null)
				return new ResponseEntity<>(admin, HttpStatus.CREATED);
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@DeleteMapping(value = "/borrar/{id}")
	public ResponseEntity<String> borrar(@PathVariable int id, @RequestHeader("Authorization") String token) {
		if (JWTAuthorizationFilter.verificarTokenContieneAutorizacion(token, "ROLE_ADMIN")) {
			if (administradorServicio.delete(id))
				return new ResponseEntity<>("Borrado id: " + id, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
}
