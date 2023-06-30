package com.example.demo.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.ReporteViajeIdCountMonopatinDto;
import com.example.demo.dtos.ViajeMonopatinUsuarioDto;
import com.example.demo.model.Localidad;
import com.example.demo.model.Monopatin;
import com.example.demo.model.Parada;
import com.example.demo.model.Tarifa;
import com.example.demo.model.Viaje;
import com.example.demo.repository.MonopatinRepository;
import com.example.demo.repository.ParadaRepository;
import com.example.demo.repository.TarifaRepository;
import com.example.demo.repository.ViajeRepository;

@Service
public class ViajeServicio {

	private ViajeRepository viajeRepository;
	private MonopatinRepository monopatinRepository;
	private ParadaRepository paradaRepository;
	private TarifaRepository tarifaRepository;
	private static final int TIEMPO_LIMITE_PAUSA = 15;
	private static final int VELOCIDAD_POR_HORA = 20;

	public ViajeServicio(@Qualifier("viajeRepository") ViajeRepository viajeRepository,
			@Qualifier("monopatinRepository") MonopatinRepository monopatinRepository,
			@Qualifier("paradaRepository") ParadaRepository paradaRepository,
			@Qualifier("tarifaRepository") TarifaRepository tarifaRepository) {
		this.monopatinRepository = monopatinRepository;
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
		return viajeRepository.findById(id);
	}

	public boolean delete(int id) {
		Viaje viaje = viajeRepository.findById(id);
		if (viaje != null) {
			viajeRepository.delete(viaje);
			return true;
		} else
			return false;
	}

	public Viaje registrarViaje(ViajeMonopatinUsuarioDto vmudto, double saldoMaximo) {
		Viaje viaje = new Viaje();
		System.out.println(vmudto);
		System.out.println(saldoMaximo);
		Monopatin monopatin = monopatinRepository.findByIdMonopatin(vmudto.getIdMonopatin());
		Parada paradaComienzo = paradaRepository.findById(vmudto.getIdParadaComienzo());
		Parada paradaDestino = paradaRepository.findById(vmudto.getIdParadaDestino());
		Tarifa tarifaActual = tarifaRepository.findTopByOrderByIdTarifaDesc();
		if (paradaComienzo != null && paradaDestino != null && tarifaActual != null) {
			if (monopatin.isEnUso() && monopatin.isEstadoMantenimiento())
				return null;
			if (paradaComienzo.getIdParada() == paradaDestino.getIdParada())
				return null;
			if (monopatin != null && !monopatin.isEnUso() && !monopatin.isEstadoMantenimiento()) {
				double distancia = new Localidad(paradaComienzo.getLatitud(), paradaComienzo.getLongitud())
						.haversine(new Localidad(paradaDestino.getLatitud(), paradaDestino.getLongitud()));
				double precioEstimado = (Math.floor(distancia * tarifaActual.getTarifaRegular() * 100) / 100);
				if (saldoMaximo > precioEstimado) {
					viaje.setMonopatin(monopatin);
					monopatin.setEnUso(true);
					viaje.setIdUsuario(vmudto.getIdUsuario());
					viaje.setParadaInicio(paradaComienzo);
					viaje.setParadaDestino(paradaDestino);
					viaje.setFechaInicio(LocalDate.now());
					viaje.setDistanciaEstimada(distancia);
					viaje.setPrecioEstimado(precioEstimado);
					viaje.setTarifaRegular(tarifaActual.getTarifaRegular());
					viaje.setTarifaPausa(tarifaActual.getTarifaPausa());
					monopatinRepository.save(monopatin);
					return viajeRepository.save(viaje);
				}
			}
			return null;
		}
		return null;

	}

	public void registrarDesvio(int id, int kilometros) {
		Viaje viaje = viajeRepository.findById(id);
		if (viaje != null) {
			viaje.setDistanciaDesvio(viaje.getDistanciaDesvio() + kilometros);
			viajeRepository.save(viaje);
		}
	}

	public void agregarTiempoPausa(int id, int tiempoPausa) {
		Viaje viaje = viajeRepository.findById(id);
		if (viaje != null) {
			viaje.setTiempoPausa(tiempoPausa + viaje.getTiempoPausa());
			viajeRepository.save(viaje);
		}
	}

	public Viaje registrarLlegada(int id) {
		Viaje viaje = viajeRepository.findById(id);
		if (viaje != null) {
			Monopatin monopatin = monopatinRepository.findByIdMonopatin(viaje.getMonopatin().getIdMonopatin());
			Parada paradaInicio = paradaRepository.findById(viaje.getParadaInicio().getIdParada());
			Parada paradaFinal = paradaRepository.findById(viaje.getParadaDestino().getIdParada());
			paradaInicio.getListaMonopatines().remove(monopatin);
			paradaFinal.getListaMonopatines().add(monopatin);
			double distanciaFinal = viaje.getDistanciaDesvio() + viaje.getDistanciaEstimada();
			monopatin.setEnUso(false);
			monopatin.setKilometrosRecorridos(distanciaFinal + monopatin.getKilometrosRecorridos());
			monopatin.setTiempoUso(distanciaFinal / VELOCIDAD_POR_HORA);
			monopatin.setTiempoPausaTotal(viaje.getTiempoPausa());
			monopatin.setCantidadViajes(monopatin.getCantidadViajes() + 1);
			if (viaje.getTiempoPausa() < TIEMPO_LIMITE_PAUSA)
				viaje.setPrecioFinal(viaje.getTarifaRegular() * distanciaFinal);
			else
				viaje.setPrecioFinal(viaje.getTarifaPausa() * distanciaFinal);
			viaje.setFechaFin(LocalDate.now());
			paradaRepository.save(paradaInicio);
			monopatinRepository.save(monopatin);
			paradaRepository.save(paradaFinal);
			return viajeRepository.save(viaje);
		}
		return null;
	}

	public List<Viaje> findAll() {
		return viajeRepository.findAll();
	}

	public List<ReporteViajeIdCountMonopatinDto> obtenerReportePorAnioConCantidadMinima(long cantidad, Integer anio) {
		List<ReporteViajeIdCountMonopatinDto> lista = viajeRepository.obtenerReportePorAnioConCantidadMinima(cantidad,
				anio);
		if (lista != null) {
			return lista;
		}
		return null;
	}

	public List<String> reporteFacturadoEntreDosFechas(LocalDate fecha1, LocalDate fecha2) {
		List<String> lista = viajeRepository.reporteFacturadoEntreDosFechas(fecha1, fecha2);
		if (lista != null) {
			return lista;
		}
		return null;
	}

	public boolean verificarTokenContieneEmail(String email, String token) {
		
		return token.contains(email);
	}
}
