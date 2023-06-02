package com.example.demo.services;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioServicio {

	private UsuarioRepository usuarioRepository;

	public UsuarioServicio(@Qualifier("repository") UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public Usuario findByIdUsuario(int idUsuario) {
		return usuarioRepository.findByIdUsuario(idUsuario);
	}

	public boolean delete(int id) {
		Usuario usuario = usuarioRepository.findByIdUsuario(id);
		if (usuario != null) {
			usuarioRepository.delete(usuario);
			return true;
		} else
			return false;
	}

	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

}
