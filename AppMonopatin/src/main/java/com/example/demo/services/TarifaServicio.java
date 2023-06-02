package com.example.demo.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.model.Monopatin;
import com.example.demo.model.Tarifa;
import com.example.demo.repository.TarifaRepository;

@Service
public class TarifaServicio {

	private TarifaRepository tarifaRepository;

	public TarifaServicio(@Qualifier("tarifaRepository") TarifaRepository tarifaRepository) {
		this.tarifaRepository = tarifaRepository;
	}

	public List<Tarifa> findAll() {
		return tarifaRepository.findAll();
	}

	public Tarifa save(Tarifa tarifa) {
		return tarifaRepository.save(tarifa);
	}

	public Tarifa findByIdTarifa(int id) {
		return tarifaRepository.findByIdTarifa(id);
	}

	public boolean delete(int id) {
		Tarifa tarifa = tarifaRepository.findByIdTarifa(id);
		if (tarifa != null) {
			tarifaRepository.delete(tarifa);
			return true;
		} else
			return false;
	}

	public float obtenerTarifaRegular() {
		List<Tarifa> tarifas = tarifaRepository.findAll();
		Collections.sort(tarifas, (a, b) -> a.getFecha().compareTo(b.getFecha()));
		return tarifas.get(0).getTarifaRegular();
	}

	public float obtenerTarifaPausa() {
		List<Tarifa> tarifas = tarifaRepository.findAll();
		Collections.sort(tarifas, (a, b) -> a.getFecha().compareTo(b.getFecha()));
		return tarifas.get(0).getTarifaPausa();
	}
}
