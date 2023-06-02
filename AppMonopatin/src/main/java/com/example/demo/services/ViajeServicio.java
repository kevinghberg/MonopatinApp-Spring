package com.example.demo.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.ViajeMonopatinUsuarioDto;
import com.example.demo.model.Localidad;
import com.example.demo.model.Monopatin;
import com.example.demo.model.Parada;
import com.example.demo.model.Tarifa;
import com.example.demo.model.Usuario;
import com.example.demo.model.Viaje;
import com.example.demo.repository.MonopatinRepository;
import com.example.demo.repository.ParadaRepository;
import com.example.demo.repository.TarifaRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.repository.ViajeRepository;

@Service
public class ViajeServicio {

	private ViajeRepository viajeRepository;
	private UsuarioRepository usuarioRepository;
	private MonopatinRepository monopatinRepository;
	private ParadaRepository paradaRepository;
	private TarifaRepository tarifaRepository;

	public ViajeServicio(@Qualifier("viajeRepository") ViajeRepository viajeRepository,
			@Qualifier("usuarioRepository") UsuarioRepository usuarioRepository,
			@Qualifier("monopatinRepository") MonopatinRepository monopatinRepository,
			@Qualifier("paradaRepository") ParadaRepository paradaRepository,
			@Qualifier("tarifaRepository") TarifaRepository tarifaRepository) {
		this.monopatinRepository = monopatinRepository;
		this.usuarioRepository = usuarioRepository;
		this.viajeRepository = viajeRepository;
		this.paradaRepository = paradaRepository;
		this.tarifaRepository = tarifaRepository;
	}

	public List<Viaje> getViajes() {
		return viajeRepository.findAll();
	}

	public Viaje save(Viaje viaje) {
		return viajeRepository.save(viaje);
	}

	public Viaje findByIdViaje(int id) {
		return viajeRepository.findByIdViaje(id);
	}

	public boolean delete(int id) {
		Viaje viaje = viajeRepository.findByIdViaje(id);
		if (viaje != null) {
			viajeRepository.delete(viaje);
			return true;
		} else
			return false;
	}

	public Viaje reservarMonopatin(ViajeMonopatinUsuarioDto vmudto) {
		Viaje viaje = new Viaje();
		Usuario usuario = usuarioRepository.findByIdUsuario(vmudto.getIdUsuario());
		Monopatin monopatin = monopatinRepository.findByIdMonopatin(vmudto.getIdMonopatin());
		Parada paradaComienzo = paradaRepository.findById(vmudto.getIdParadaComienzo());
		Parada paradaDestino = paradaRepository.findById(vmudto.getIdParadaDestino());
		if (usuario != null && monopatin != null && paradaComienzo != null && paradaDestino != null) {
			double distancia = new Localidad(paradaComienzo.getLatitud(), paradaComienzo.getLongitud())
					.distanceTo(new Localidad(paradaDestino.getLatitud(), paradaDestino.getLongitud()));
			viaje.setMonopatin(monopatin);
			viaje.setUsuario(usuario);
			viaje.setPrecioEstimado(distancia*1);
			//TODO ESTIMAR PRECIO
			viaje.setFechaInicio(LocalDate.now());
			return (viajeRepository.save(viaje));
		} else
			return null;
	}

	public List<Viaje> findAll() {
		return viajeRepository.findAll();
	}

}
