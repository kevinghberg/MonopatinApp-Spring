package com.example.demo.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.model.CuentaMercadoPago;
import com.example.demo.model.Usuario;
import com.example.demo.repository.CuentaMercadoPagoRepository;
import com.example.demo.repository.UsuarioRepository;

@Service
public class CuentaMercadoPagoServicio {

	private CuentaMercadoPagoRepository cuentaMercadoPagoRepository;
	private UsuarioRepository usuarioRepository;

	public CuentaMercadoPagoServicio(
			@Qualifier("cuentaMercadoPagoRepository") CuentaMercadoPagoRepository cuentaMercadoPagoRepository,
			@Qualifier("usuarioRepository") UsuarioRepository usuarioRepository) {
		this.cuentaMercadoPagoRepository = cuentaMercadoPagoRepository;
		this.usuarioRepository = usuarioRepository;
	}

	public CuentaMercadoPago findById(int idCuentaMP) {
		return cuentaMercadoPagoRepository.findById(idCuentaMP);
	}

	public CuentaMercadoPago save(CuentaMercadoPago cuenta) {
		if (cuentaMercadoPagoRepository.findByEmail(cuenta.getEmail()) != null) {
			return null;
		} else {
			cuenta.setFechaAlta(LocalDate.now());
			return cuentaMercadoPagoRepository.save(cuenta);
		}
	}

	public CuentaMercadoPago agregarRelacionUsuario(int idUsuario, int idCuentaMP) {
		Usuario usuario = usuarioRepository.findByIdUsuario(idUsuario);
		CuentaMercadoPago cuenta = cuentaMercadoPagoRepository.findById(idCuentaMP);
		if (usuario != null && cuenta != null) {
			cuenta.getListaUsuario().add(usuario);
			return cuentaMercadoPagoRepository.save(cuenta);
		} else {
			return null;
		}
	}

	public List<CuentaMercadoPago> findAll() {
		return cuentaMercadoPagoRepository.findAll();
	}

	public boolean delete(int id) {
		CuentaMercadoPago cuentamp = cuentaMercadoPagoRepository.findById(id);
		if (cuentamp != null) {
			cuentaMercadoPagoRepository.delete(cuentamp);
			return true;
		} else
			return false;
	}
	
	public CuentaMercadoPago debitarDinero(int idUsuario, double dineroADebitar) {
		Usuario usuario = usuarioRepository.findByIdUsuario(idUsuario);
		CuentaMercadoPago cuenta = obtenerCuentaMayorSaldo(usuario);
		cuenta.setSaldo(cuenta.getSaldo() - dineroADebitar);
		return cuentaMercadoPagoRepository.save(cuenta);
	}
	
	public CuentaMercadoPago obtenerCuentaMayorSaldo(Usuario usuario) {
		List<CuentaMercadoPago> cuentasMP = cuentaMercadoPagoRepository.findAll();
		if (cuentasMP.size() > 0) {
			CuentaMercadoPago mayor = cuentasMP.get(0);
			List<CuentaMercadoPago> result = new ArrayList<>(cuentasMP);
			result.clear();
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
			return mayor;
		} else
			return null;
	}

}
