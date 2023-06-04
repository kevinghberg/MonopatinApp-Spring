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

	public List<Administrador> findAll() {
		return administradorRepository.findAll();
	}

	public Administrador save(Administrador admin) {
		return administradorRepository.save(admin);
	}

	public boolean delete(int id) {
		Administrador admin = administradorRepository.findById(id);
		if (admin != null) {
			administradorRepository.delete(admin);
			return true;
		} else {
			return false;
		}
	}

	public Administrador findById(int i) {
		return administradorRepository.findById(i);
	}

}
