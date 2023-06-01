package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.model.Administrador;
import com.example.demo.repository.AdministradorRepository;

@Service
public class AdministradorServicio {

	private AdministradorRepository administradorRepository;

	public AdministradorServicio(
			@Qualifier("administradorRepository") AdministradorRepository administradorRepository) {
		this.administradorRepository = administradorRepository;
	}

	public List<Administrador> obtenerListaAdmins() {
		return administradorRepository.findAll();
	}

	public Administrador agregarAdmin(Administrador admin) {
		return administradorRepository.save(admin);
	}

	public boolean borrar(int id) {
		Administrador admin = administradorRepository.findById(id);
		if (admin != null) {
			administradorRepository.delete(admin);
			return true;
		} else {
			return false;
		}
	}

}
