package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.RelacionUsuarioMPDto;
import com.example.demo.model.CuentaMercadoPago;
import com.example.demo.model.Usuario;
import com.example.demo.repository.CuentaMercadoPagoRepository;
import com.example.demo.repository.UsuarioRepository;

@RestController
@RequestMapping("cuentasmp")
public class CuentaMercadoPagoController {

	@Qualifier("cuentaMercadoPagoRepository")
	@Autowired

	private CuentaMercadoPagoRepository repository;
	private UsuarioRepository usuarioRepository;

	public CuentaMercadoPagoController(@Qualifier("cuentaMercadoPagoRepository") CuentaMercadoPagoRepository cuentaMP,
			@Qualifier("usuarioRepository") UsuarioRepository usuario) {
		this.usuarioRepository = usuario;
		this.repository = cuentaMP;
	}

	@GetMapping("/")
	public List<CuentaMercadoPago> getCuentasMP() {
		return repository.findAll();
	}

	@PostMapping(value = "/agregar/", headers = "content-type=application/json")
	public CuentaMercadoPago agregar(@RequestBody CuentaMercadoPago cuentaMP) {
		cuentaMP.setFechaAlta(LocalDate.now());
		return repository.save(cuentaMP);
	}

	@PostMapping(value = "/relacion/",headers = "content-type=application/json")
	public CuentaMercadoPago agregarRelacion(@RequestBody RelacionUsuarioMPDto relacion) {
		Usuario usuario = usuarioRepository.findByIdUsuario(relacion.getIdUsuario());
		CuentaMercadoPago cuenta = repository.findById(relacion.getIdCuentaMP());
		cuenta.getListaUsuario().add(usuario);
		return repository.save(cuenta);
	}
}