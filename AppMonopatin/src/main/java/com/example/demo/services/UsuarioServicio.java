package com.example.demo.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioServicio {

	private UsuarioRepository repository;

	public UsuarioServicio(@Qualifier("repository") UsuarioRepository usuarioRepository) {
		this.repository = repository;
	}

	public Usuario obtenerUsuarioPorId(int idUsuario) {
		return repository.findByIdUsuario(idUsuario);
	}

	public Usuario findByIdUsuario(int idUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

}
