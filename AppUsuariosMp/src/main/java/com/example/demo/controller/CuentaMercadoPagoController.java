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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CuentaMercadoPago;
import com.example.demo.services.CuentaMercadoPagoServicio;
import com.example.demo.services.UsuarioServicio;

@RestController
@RequestMapping("cuentasmp")
public class CuentaMercadoPagoController {

	@Qualifier("cuentaMercadoPagoServicio")
	@Autowired

	private CuentaMercadoPagoServicio cuentaMercadoPagoServicio;
	private UsuarioServicio usuarioServicio;

	public CuentaMercadoPagoController(
			@Qualifier("cuentaMercadoPagoServicio") CuentaMercadoPagoServicio cuentaMercadoPagoServicio,
			@Qualifier("usuarioServicio") UsuarioServicio usuarioServicio) {
		this.cuentaMercadoPagoServicio = cuentaMercadoPagoServicio;
		this.usuarioServicio = usuarioServicio;
	}

	@GetMapping("/")
	public List<CuentaMercadoPago> getCuentasMP() {
		return cuentaMercadoPagoServicio.findAll();
	}

	@PostMapping(value = "/agregar", headers = "content-type=application/json")
	public CuentaMercadoPago agregar(@RequestBody CuentaMercadoPago cuentaMP) {
		return cuentaMercadoPagoServicio.save(cuentaMP);
	}

	@PostMapping(value = "/relacion/{idCuentaMp}/{idUsuario}", headers = "content-type=application/json")
	public ResponseEntity<CuentaMercadoPago> agregarRelacion(@PathVariable("idCuentaMp") int idCuentaMp,
			@PathVariable("idUsuario") int idUsuario) {
		CuentaMercadoPago cuenta = cuentaMercadoPagoServicio.agregarRelacionUsuario(idCuentaMp, idUsuario);
		if (cuenta != null) {
			return new ResponseEntity<>(cuenta, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(value = "/borrar/{id}")
	public ResponseEntity<String> borrar(@PathVariable int id) {
		if (cuentaMercadoPagoServicio.delete(id))
			return new ResponseEntity<>("Borrado id: " + id, HttpStatus.OK);
		else
			return new ResponseEntity<>("No borrado", HttpStatus.BAD_REQUEST);
	}

	@PutMapping(value="/debitar_dinero/{id}/{dinero}")
	public ResponseEntity< CuentaMercadoPago> debitarDinero(@PathVariable int id, @PathVariable double dinero){
		CuentaMercadoPago cuenta = cuentaMercadoPagoServicio.debitarDinero(id, dinero);
		return new ResponseEntity<>(cuenta,HttpStatus.OK);
	}
}