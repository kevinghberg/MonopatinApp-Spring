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
	private static final int TIEMPO_LIMITE_PAUSA = 15;
	private static final int VELOCIDAD_POR_HORA = 20;

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
		if (paradaComienzo.getIdParada() == paradaDestino.getIdParada()) {
			return null;
		}
		Tarifa tarifaActual = tarifaRepository.findTopByOrderByIdTarifaDesc();
		List<CuentaMercadoPago> cuentasMP = cuentaMercadoPagoRepository.findAll();
		CuentaMercadoPago cuenta = obtenerCuentaMayorSaldo(usuario, cuentasMP);
		if (usuario != null && paradaComienzo != null && paradaDestino != null && tarifaActual != null
				&& cuenta != null) {
			if (monopatin != null && !monopatin.isEnUso() && !monopatin.isEstadoMantenimiento()) {
				double distancia = new Localidad(paradaComienzo.getLatitud(), paradaComienzo.getLongitud())
						.haversine(new Localidad(paradaDestino.getLatitud(), paradaDestino.getLongitud()));
				double precioEstimado = (Math.floor(distancia * tarifaActual.getTarifaRegular() * 100) / 100);
				if (cuenta.getSaldo() > precioEstimado) {
					viaje.setMonopatin(monopatin);
					monopatin.setEnUso(true);
					viaje.setUsuario(usuario);
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

	public CuentaMercadoPago obtenerCuentaMayorSaldo(Usuario usuario, List<CuentaMercadoPago> cuentasMP) {
		if (cuentasMP.size() > 0) {
			CuentaMercadoPago mayor = new CuentaMercadoPago(0);
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

	public Viaje registrarLlegada(int id) {
		Viaje viaje = viajeRepository.findById(id);
		if (viaje != null) {
			Monopatin monopatin = monopatinRepository.findByIdMonopatin(viaje.getMonopatin().getIdMonopatin());
			monopatin.setEnUso(false);
			Parada paradaInicio = paradaRepository.findById(viaje.getParadaInicio().getIdParada());
			Parada paradaFinal = paradaRepository.findById(viaje.getParadaDestino().getIdParada());
			paradaInicio.getListaMonopatines().remove(monopatin);
			paradaFinal.getListaMonopatines().add(monopatin);
			double distanciaFinal = viaje.getDistanciaDesvio() + viaje.getDistanciaEstimada();
			monopatin.setKilometrosRecorridos(distanciaFinal + monopatin.getKilometrosRecorridos());
			monopatin.setTiempoUso(distanciaFinal / VELOCIDAD_POR_HORA);
			if (viaje.getTiempoPausa() < TIEMPO_LIMITE_PAUSA)
				viaje.setPrecioFinal(viaje.getTarifaRegular() * distanciaFinal);
			else
				viaje.setPrecioFinal(viaje.getTarifaPausa() * distanciaFinal);
			viaje.setFechaFin(LocalDate.now());
			debitarDinero(viaje.getUsuario(), viaje.getPrecioFinal());
			paradaRepository.save(paradaInicio);
			monopatinRepository.save(monopatin);
			paradaRepository.save(paradaFinal);
			return viajeRepository.save(viaje);
		}
		return null;
	}

	private void debitarDinero(Usuario usuario, double dineroADebitar) {
		List<CuentaMercadoPago> cuentasMP = cuentaMercadoPagoRepository.findAll();
		CuentaMercadoPago cuenta = obtenerCuentaMayorSaldo(usuario, cuentasMP);
		cuenta.setSaldo(cuenta.getSaldo() - dineroADebitar);
		cuentaMercadoPagoRepository.save(cuenta);
	}

	public List<Viaje> findAll() {
		return viajeRepository.findAll();
	}
}
