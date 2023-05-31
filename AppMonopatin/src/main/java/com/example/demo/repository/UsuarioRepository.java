package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Monopatin;
import com.example.demo.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public List<Usuario> findAll();

	public Usuario findByIdUsuario(int idUsuario);

}
