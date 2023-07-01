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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("cuentasmp")
@Api(tags = "🏧 CuentaMP Controller")
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

	@ApiOperation(value = "Obtiene la lista de cuentas de Mercado Pago",
            notes = "📄 Obtiene la lista de todas las cuentas de Mercado Pago")
    @GetMapping("/")
	public List<CuentaMercadoPago> getCuentasMP() {
		return cuentaMercadoPagoServicio.findAll();
	}

    @ApiOperation(value = "Agrega una cuenta de Mercado Pago",
            notes = "➕ Agrega una nueva cuenta de Mercado Pago")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "✅ Cuenta de Mercado Pago agregada exitosamente")
    })
    @PostMapping(value = "/agregar", headers = "content-type=application/json")
	public CuentaMercadoPago agregar(@RequestBody CuentaMercadoPago cuentaMP) {
		return cuentaMercadoPagoServicio.save(cuentaMP);
	}

    @ApiOperation(value = "Agrega una relación entre una cuenta de Mercado Pago y un usuario",
            notes = "➕ Agrega una relación entre una cuenta de Mercado Pago y un usuario según sus IDs")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "✅ Relación agregada exitosamente"),
            @ApiResponse(code = 400, message = "❌ No se pudo agregar la relación")
    })
    @PostMapping(value = "/relacion/{idCuentaMp}/{idUsuario}", headers = "content-type=application/json")
	public ResponseEntity<CuentaMercadoPago> agregarRelacion(@PathVariable("idCuentaMp") int idCuentaMp,
			@PathVariable("idUsuario") int idUsuario) {
		CuentaMercadoPago cuenta = cuentaMercadoPagoServicio.agregarRelacionUsuario(idCuentaMp, idUsuario);
		if (cuenta != null) {
			return new ResponseEntity<>(cuenta, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

    @ApiOperation(value = "Elimina una cuenta de Mercado Pago",
            notes = "❌ Elimina una cuenta de Mercado Pago según su ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "✅ Cuenta de Mercado Pago borrada exitosamente"),
            @ApiResponse(code = 400, message = "❌ No se pudo borrar la cuenta de Mercado Pago")
    })
    @DeleteMapping(value = "/borrar/{id}")
	public ResponseEntity<String> borrar(@PathVariable int id) {
		if (cuentaMercadoPagoServicio.delete(id))
			return new ResponseEntity<>("Borrado id: " + id, HttpStatus.OK);
		else
			return new ResponseEntity<>("No borrado", HttpStatus.BAD_REQUEST);
	}

    @ApiOperation(value = "Debita dinero de una cuenta de Mercado Pago",
            notes = "💸 Debita una cantidad de dinero de una cuenta de Mercado Pago según su ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "✅ Dinero debitado exitosamente")
    })
    @PutMapping(value = "/debitar_dinero/{id}/{dinero}")
	public ResponseEntity< CuentaMercadoPago> debitarDinero(@PathVariable int id, @PathVariable double dinero){
		CuentaMercadoPago cuenta = cuentaMercadoPagoServicio.debitarDinero(id, dinero);
		return new ResponseEntity<>(cuenta,HttpStatus.OK);
	}
}