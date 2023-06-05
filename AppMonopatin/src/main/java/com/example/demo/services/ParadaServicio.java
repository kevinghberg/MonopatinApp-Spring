package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.ParadaMonopatinDto;
import com.example.demo.model.Localidad;
import com.example.demo.model.Monopatin;
import com.example.demo.model.Parada;
import com.example.demo.repository.MonopatinRepository;
import com.example.demo.repository.ParadaRepository;

@Service
public class ParadaServicio {

	private ParadaRepository paradaRepository;
	private MonopatinRepository monopatinRepository;

	public ParadaServicio(@Qualifier("paradaRepository") ParadaRepository paradaRepository,
			@Qualifier("monopatinRepository") MonopatinRepository monopatinRepository) {
		this.paradaRepository = paradaRepository;
		this.monopatinRepository = monopatinRepository;
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

	public boolean delete(int id) {
		Parada parada = paradaRepository.findById(id);
		if (parada != null) {
			paradaRepository.delete(parada);
			return true;
		} else
			return false;
	}

	public Parada agregarRelacionMonopatin(ParadaMonopatinDto relacion) {
		Parada parada = paradaRepository.findById(relacion.getIdParada());
		List<Parada> lista = paradaRepository.findAll();
		Monopatin monopatin = monopatinRepository.findByIdMonopatin(relacion.getIdMonopatin());
		for (Parada pr : lista) {
			if (pr.getListaMonopatines().contains(monopatin)) {
				pr.getListaMonopatines().remove(monopatin);
			}
		}
		if (parada != null && monopatin != null) {
			parada.getListaMonopatines().add(monopatin);
			monopatinRepository.save(monopatin);
			return parada;
		} else {
			return null;
		}
	}

	public List<Parada> obtenerParadasCercanas(Double longitud, Double latitud, double distancia) {
		List<Parada> listaParadas = paradaRepository.findAll();
		for (int i = 0; i < listaParadas.size(); i++) {
			Localidad localidad = new Localidad(latitud, longitud);
			Localidad localidad2 = new Localidad(listaParadas.get(i).getLatitud(), listaParadas.get(i).getLongitud());
			double diferencia = localidad.haversine(localidad2);
			if (diferencia > distancia) {
				listaParadas.remove(i);
			}
		}
		return listaParadas;
	}

}
