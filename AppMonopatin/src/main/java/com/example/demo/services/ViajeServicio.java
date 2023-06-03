package com.example.demo.services;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.ViajeMonopatinUsuarioDto;
import com.example.demo.model.CuentaMercadoPago;
import com.example.demo.model.Localidad;
import com.example.demo.model.Monopatin;
import com.example.demo.model.Parada;
import com.example.demo.model.Tarifa;
import com.example.demo.model.Usuario;
import com.example.demo.model.Viaje;
import com.example.demo.repository.CuentaMercadoPagoRepository;
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
	private CuentaMercadoPagoRepository cuentaMercadoPagoRepository;
	private static final int VELOCIDAD = 20;

	public ViajeServicio(@Qualifier("viajeRepository") ViajeRepository viajeRepository,
			@Qualifier("usuarioRepository") UsuarioRepository usuarioRepository,
			@Qualifier("monopatinRepository") MonopatinRepository monopatinRepository,
			@Qualifier("paradaRepository") ParadaRepository paradaRepository,
			@Qualifier("tarifaRepository") TarifaRepository tarifaRepository,
			@Qualifier("cuentaMercadoPagoRepository") CuentaMercadoPagoRepository cuentaMercadoPagoRepository) {
		this.monopatinRepository = monopatinRepository;
		this.usuarioRepository = usuarioRepository;
		this.viajeRepository = viajeRepository;
		this.paradaRepository = paradaRepository;
		this.tarifaRepository = tarifaRepository;
		this.cuentaMercadoPagoRepository = cuentaMercadoPagoRepository;
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

	public Viaje registrarViaje(ViajeMonopatinUsuarioDto vmudto) {
		Viaje viaje = new Viaje();
		Usuario usuario = usuarioRepository.findByIdUsuario(vmudto.getIdUsuario());
		Monopatin monopatin = monopatinRepository.findByIdMonopatin(vmudto.getIdMonopatin());
		Parada paradaComienzo = paradaRepository.findById(vmudto.getIdParadaComienzo());
		Parada paradaDestino = paradaRepository.findById(vmudto.getIdParadaDestino());
		Tarifa tarifaActual = tarifaRepository.findTopByOrderByIdTarifaDesc();
		/*
		 * List<CuentaMercadoPago> cuentasMP = cuentaMercadoPagoRepository.findAll();
		 * CuentaMercadoPago cuenta = obtenerCuentaMayorSaldo(usuario, cuentasMP);
		 * System.out.println("cuenta:"+cuenta);
		 */
		if (usuario != null && paradaComienzo != null && paradaDestino != null && tarifaActual != null
		/* && cuenta != null */) {
			if (monopatin != null && !monopatin.isEnUso() && !monopatin.isEstadoMantenimiento()) {
				double distancia = new Localidad(paradaComienzo.getLatitud(), paradaComienzo.getLongitud())
						.haversine(new Localidad(paradaDestino.getLatitud(), paradaDestino.getLongitud()));
				double precioEstimado = (Math.floor(distancia * tarifaActual.getTarifaRegular() * 100) / 100);
				/* if (cuenta.getSaldo() > precioEstimado) { */
				viaje.setMonopatin(monopatin);
				monopatin.setEnUso(true);
				viaje.setUsuario(usuario);
				viaje.setParadaInicio(paradaComienzo);
				viaje.setParadaDestino(paradaDestino);
				viaje.setFechaInicio(LocalDate.now());
				viaje.setDistanciaEstimada(distancia);
				viaje.setPrecioEstimado(precioEstimado);
				return viajeRepository.save(viaje);

			}
			return null;
		}
		return null;

	}

	public void registrarAvanceMonopatin(int id, int kilometros) {
		Viaje viaje = viajeRepository.findById(id);
		if (viaje != null) {
			viaje.setDistanciaRecorrida(kilometros + viaje.getDistanciaRecorrida());
			viajeRepository.save(viaje);
		}

	}

	public CuentaMercadoPago obtenerCuentaMayorSaldo(Usuario usuario, List<CuentaMercadoPago> cuentasMP) {
		if (cuentasMP.size() > 0) {
			CuentaMercadoPago mayor = new CuentaMercadoPago(0);
			System.out.println(cuentasMP.get(0).getListaUsuario());
			for (CuentaMercadoPago cuenta : cuentasMP) {
				if (cuenta.getListaUsuario().size() > 0) {
					for (Usuario user : cuenta.getListaUsuario()) {
						if (user.getIdUsuario() == usuario.getIdUsuario()) {
							if (cuenta.getSaldo() > mayor.getSaldo())
								mayor = cuenta;
						}
					}
				}
			}
			if (mayor.getSaldo() > 0)
				return mayor;
			else
				return null;
		} else
			return null;
	}

	public List<Viaje> findAll() {
		return viajeRepository.findAll();
	}

	public Viaje registrarLlegada(int id) {
		Viaje viaje = viajeRepository.findById(id);
		System.out.println(viaje);
		if (viaje != null) {
			Monopatin monopatin = monopatinRepository.findByIdMonopatin(viaje.getMonopatin().getIdMonopatin());
			monopatin.setEnUso(false);
			Parada paradaInicio = paradaRepository.findById(viaje.getParadaInicio().getIdParada());
			Parada paradaFinal = paradaRepository.findById(viaje.getParadaDestino().getIdParada());
			if (paradaInicio.getListaMonopatines().contains(monopatin)) 
				paradaInicio.getListaMonopatines().remove(monopatin);
			paradaFinal.getListaMonopatines().add(monopatin);
			if (viaje.getDistanciaRecorrida() == 0) {
				monopatin.setKilometrosRecorridos(viaje.getDistanciaEstimada());
				monopatin.setTiempoUso(viaje.getDistanciaEstimada() / VELOCIDAD);
				double estimadoxestimado = viaje.getPrecioEstimado() * viaje.getDistanciaEstimada();
				System.out.println(estimadoxestimado);
				viaje.setPrecioFinal(estimadoxestimado);
			} else {
				monopatin.setKilometrosRecorridos(viaje.getDistanciaRecorrida());
				monopatin.setTiempoUso(viaje.getDistanciaRecorrida() / VELOCIDAD);
				double estimadoxrecorrido = viaje.getPrecioEstimado() * viaje.getDistanciaRecorrida();
				System.out.println(estimadoxrecorrido);
				viaje.setPrecioFinal(estimadoxrecorrido);
			}
			viaje.setFechaFin(LocalDate.now());
			return viajeRepository.save(viaje);
		}
		return null;
	}

}
