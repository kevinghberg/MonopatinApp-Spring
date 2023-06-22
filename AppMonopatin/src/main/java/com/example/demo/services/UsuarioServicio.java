package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.demo.model.CuentaMercadoPago;
import com.example.demo.model.Usuario;
import com.example.demo.repository.CuentaMercadoPagoRepository;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioServicio {

	private UsuarioRepository usuarioRepository;
	private CuentaMercadoPagoRepository cuentaMercadoPagoRepository;

	public UsuarioServicio(@Qualifier("usuarioRepository") UsuarioRepository usuarioRepository,
			@Qualifier("cuentaMercadoPagoRepository") CuentaMercadoPagoRepository cuentaMercadoPagoRepository) {
		this.cuentaMercadoPagoRepository = cuentaMercadoPagoRepository;
		this.usuarioRepository = usuarioRepository;
	}

	public Usuario findByIdUsuario(int idUsuario) {
		return usuarioRepository.findByIdUsuario(idUsuario);
	}

	public boolean delete(int id) {
		Usuario usuario = usuarioRepository.findByIdUsuario(id);
		if (usuario != null) {
			usuarioRepository.delete(usuario);
			return true;
		} else
			return false;
	}

	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	public Usuario save(Usuario usuario) {
		if (usuarioRepository.findByEmail(usuario.getEmail())!=null) {
			return usuario;
		}
		return usuarioRepository.save(usuario);
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
