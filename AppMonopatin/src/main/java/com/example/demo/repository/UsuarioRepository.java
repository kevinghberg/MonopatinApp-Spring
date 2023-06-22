package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Monopatin;
import com.example.demo.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Qualifier("usuarioRepository")
	@Autowired
	
	public List<Usuario> findAll();

	public Usuario findByIdUsuario(int idUsuario);

	public Usuario findByEmail(String email);

}
