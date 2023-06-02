package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.RelacionUsuarioMPDto;
import com.example.demo.model.CuentaMercadoPago;
import com.example.demo.services.CuentaMercadoPagoServicio;

@RestController
@RequestMapping("cuentasmp")
public class CuentaMercadoPagoController {

	@Qualifier("cuentaMercadoPagoServicio")
	@Autowired

	private CuentaMercadoPagoServicio cuentaMercadoPagoServicio;

	public CuentaMercadoPagoController(
			@Qualifier("cuentaMercadoPagoServicio") CuentaMercadoPagoServicio cuentaMercadoPagoServicio) {
		this.cuentaMercadoPagoServicio = cuentaMercadoPagoServicio;
	}

	@GetMapping("/")
	public List<CuentaMercadoPago> getCuentasMP() {
		return cuentaMercadoPagoServicio.findAll();
	}

	@PostMapping(value = "/agregar", headers = "content-type=application/json")
	public CuentaMercadoPago agregar(@RequestBody CuentaMercadoPago cuentaMP) {
		return cuentaMercadoPagoServicio.save(cuentaMP);
	}

	@PostMapping(value = "/relacion", headers = "content-type=application/json")
	public ResponseEntity<String> agregarRelacion(@RequestBody RelacionUsuarioMPDto relacion) {
		if (cuentaMercadoPagoServicio.agregarRelacionUsuario(relacion) != null) {
			return new ResponseEntity<>("Agregado", HttpStatus.OK);
		} else
			return new ResponseEntity<>("Error en la carga", HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(value = "/borrar/{id}")
	public ResponseEntity<String> borrar(@PathVariable int id) {
		if (cuentaMercadoPagoServicio.delete(id))
			return new ResponseEntity<>("Borrado id: " + id, HttpStatus.OK);
		else
			return new ResponseEntity<>("No borrado", HttpStatus.BAD_REQUEST);
	}
}