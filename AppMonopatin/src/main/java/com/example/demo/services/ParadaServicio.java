package com.example.demo.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.model.Localidad;
import com.example.demo.model.Parada;
import com.example.demo.repository.MonopatinRepository;
import com.example.demo.repository.ParadaRepository;

@Service
public class ParadaServicio {

	private ParadaRepository paradaRepository;
	private MonopatinRepository monopatinRepository;

	public ParadaServicio(@Qualifier("paradaRepository") ParadaRepository paradaRepository,
			@Qualifier("monopatinRepository") MonopatinRepository monopatinRepository) {
		this.monopatinRepository = monopatinRepository;
		this.paradaRepository = paradaRepository;
	}

	public List<Parada> findAll() {

		return paradaRepository.findAll();
	}

	public Parada save(Parada parada) {
		return paradaRepository.save(parada);
	}

	public Parada findById(int idParada) {
		return paradaRepository.findById(idParada);
	}

	public void delete(Parada parada) {
		// TODO Auto-generated method stub

	}

	public List<Parada> obtenerParadasCercanas(Double longitud, Double latitud, double distancia) {
		List<Parada> listaParadas = paradaRepository.findAll();
		for (int i = 0; i < listaParadas.size(); i++) {
			Localidad localidad = new Localidad(latitud, longitud);
			Localidad localidad2 = new Localidad(listaParadas.get(i).getLatitud(), listaParadas.get(i).getLongitud());
			double diferencia = localidad.distanceTo(localidad2);
			System.out.println("diferencia:" + diferencia);
			if (diferencia > distancia) {
				listaParadas.remove(i);
			}
		}
		return listaParadas;
	}

}
