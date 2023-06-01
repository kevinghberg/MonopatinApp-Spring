package com.example.demo.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.ViajeMonopatinUsuarioDto;
import com.example.demo.model.Monopatin;
import com.example.demo.model.Usuario;
import com.example.demo.model.Viaje;
import com.example.demo.repository.MonopatinRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.repository.ViajeRepository;

@Service
public class ViajeServicio {

	private ViajeRepository viajeRepository;
	private UsuarioRepository usuarioRepository;
	private MonopatinRepository monopatinRepository;

	public ViajeServicio(@Qualifier("viajeRepository") ViajeRepository viajeRepository,
			@Qualifier("usuarioRepository") UsuarioRepository usuarioRepository,
			@Qualifier("monopatinRepository") MonopatinRepository monopatinRepository) {
		this.monopatinRepository = monopatinRepository;
		this.usuarioRepository = usuarioRepository;
		this.viajeRepository = viajeRepository;
	}

	public List<Viaje> getViajes() {
		return viajeRepository.findAll();
	}

	public Viaje agregarViaje(Viaje viaje) {
		return viajeRepository.save(viaje);
	}

	public Viaje findByIdViaje(int id) {
		return viajeRepository.findByIdViaje(id);
	}

	public boolean borrarViaje(int id) {
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
		if (usuario != null && monopatin != null) {
			long tarifa = vmudto.getPrecioEstimado();
			viaje.setMonopatin(monopatin);
			viaje.setUsuario(usuario);
			viaje.setPrecioEstimado(tarifa);
			viaje.setFechaInicio(LocalDate.now());
			return (viajeRepository.save(viaje));
		} else
			return null;
	}

}
