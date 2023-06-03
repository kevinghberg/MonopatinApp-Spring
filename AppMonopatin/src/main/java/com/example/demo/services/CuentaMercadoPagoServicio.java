package com.example.demo.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.RelacionUsuarioMPDto;
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
		cuenta.setFechaAlta(LocalDate.now());
		return cuentaMercadoPagoRepository.save(cuenta);
	}

	public CuentaMercadoPago agregarRelacionUsuario(RelacionUsuarioMPDto relacion) {
		Usuario usuario = usuarioRepository.findByIdUsuario(relacion.getIdUsuario());
		CuentaMercadoPago cuenta = cuentaMercadoPagoRepository.findById(relacion.getIdCuentaMP());
		if (usuario != null && cuenta != null) {
			cuenta.getListaUsuario().add(usuario);
			cuentaMercadoPagoRepository.save(cuenta);
			return cuenta;
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

}
