package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.CuentaMercadoPago;
import com.example.demo.repository.CuentaMercadoPagoRepository;

@RestController
@RequestMapping("cuentasmp")
public class CuentaMercadoPagoController {

	@Qualifier("cuentaMercadoPagoRepository")
	@Autowired

	private CuentaMercadoPagoRepository repository;

	public CuentaMercadoPagoController(@Qualifier("cuentaMercadoPagoRepository") CuentaMercadoPagoRepository cuentaMP) {
		this.repository = cuentaMP;
	}

	@GetMapping("/")
	public List<CuentaMercadoPago> getCuentasMP() {
		return repository.findAll();
	}

	@PostMapping(value = "/agregar/", headers = "content-type=application/json")
	public CuentaMercadoPago agregar(@RequestBody CuentaMercadoPago cuentaMP) {
		return repository.save(cuentaMP);
	}
}