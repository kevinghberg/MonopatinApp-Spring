package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

	@Qualifier("usuarioRepository")
	@Autowired

	private UsuarioRepository repository;

	public UsuarioController(@Qualifier("usuarioRepository") UsuarioRepository usuario) {
		this.repository = usuario;
	}

	@GetMapping("/")
	public List<Usuario> getUsuarios() {
		return repository.findAll();
	}

	@PostMapping(value = "/agregar/", headers = "content-type=application/json")
	public Usuario agregarUsuario(@RequestBody Usuario usuario) {
		return repository.save(usuario);
	}

}
