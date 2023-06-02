package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.MonopatinEstadoDto;
import com.example.demo.model.CuentaMercadoPago;
import com.example.demo.model.Monopatin;
import com.example.demo.repository.MonopatinRepository;

@Service
public class MonopatinServicio {

	private MonopatinRepository monopatinRepository;

	public MonopatinServicio(@Qualifier("monopatinRepository") MonopatinRepository monopatinRepository) {
		this.monopatinRepository = monopatinRepository;
	}

	public Monopatin findByIdMonopatin(int idMonopatin) {
		return monopatinRepository.findByIdMonopatin(idMonopatin);
	}

	public List<Monopatin> findAll() {
		return monopatinRepository.findAll();
	}

	public Monopatin save(Monopatin monopatin) {
		return monopatinRepository.save(monopatin);
	}

	public Monopatin findByPatente(String patente) {
		return monopatinRepository.findByPatente(patente);
	}

	public boolean delete(int id) {
		Monopatin monopatin = monopatinRepository.findByIdMonopatin(id);
		if (monopatin != null) {
			monopatinRepository.delete(monopatin);
			return true;
		} else
			return false;
	}

	public Monopatin actualizarEstadoEnUso(MonopatinEstadoDto mmdto) {
		Monopatin monopatin = monopatinRepository.findByIdMonopatin(mmdto.getId());
		if (monopatin != null) {
			if (mmdto.getEstado() == 1) {
				monopatin.setEnUso(true);
			} else
				monopatin.setEnUso(false);
			return monopatin;
		}
		return null;
	}

	public Monopatin actualizarEstadoMantenimiento(MonopatinEstadoDto mmdto) {
		Monopatin monopatin = monopatinRepository.findByIdMonopatin(mmdto.getId());
		if (monopatin != null) {
			if (mmdto.getEstado() == 1) {
				monopatin.setEstadoMantenimiento(false);
			} else
				monopatin.setEstadoMantenimiento(false);
			return monopatin;
		}
		return null;
	}

}
