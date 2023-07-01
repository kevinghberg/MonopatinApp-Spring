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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Tarifa;
import com.example.demo.security.JWTAuthorizationFilter;
import com.example.demo.services.TarifaServicio;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@RestController
@RequestMapping("tarifas")
@Api(tags = "💲 Tarifa Controller")
public class TarifaController {

	@Qualifier("tarifaServicio")
	@Autowired

	private TarifaServicio tarifaServicio;

	public TarifaController(@Qualifier("tarifaServicio") TarifaServicio tarifa) {
		this.tarifaServicio = tarifa;
	}

	 @ApiOperation(value = "Obtiene el historial de tarifas",
	            notes = "📜 Obtiene una lista con el historial de todas las tarifas")
	    @GetMapping("/historico")
	public List<Tarifa> getHistoricoTarifas() {
		return tarifaServicio.findAll();
	}

	 @ApiOperation(value = "Agrega una nueva tarifa",
	            notes = "➕ Agrega una nueva tarifa al historial de tarifas")
	    @ApiResponses(value = {
	            @ApiResponse(code = 201, message = "✅ Tarifa agregada exitosamente"),
	            @ApiResponse(code = 400, message = "❌ No se pudo agregar la tarifa"),
	            @ApiResponse(code = 401, message = "❌ No autorizado para realizar esta acción")
	    })
	    @PostMapping(value = "/agregar", headers = "content-type=application/json")
	public ResponseEntity<Tarifa> agregar(@RequestBody Tarifa tarifa, @RequestHeader("Authorization") String token) {
		if (JWTAuthorizationFilter.verificarTokenContieneAutorizacion(token, "ROLE_ADMIN"))
			if (tarifaServicio.save(tarifa) != null)
				return new ResponseEntity<>(tarifa, HttpStatus.CREATED);
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	 @ApiOperation(value = "Obtiene la última tarifa",
	            notes = "🔄 Obtiene la última tarifa registrada en el historial")
	    @GetMapping(value = "/ultima")
	public Tarifa obtenerUltimaTarifa() {
		return tarifaServicio.findTopByOrderByIdTarifaDesc();
	}

	  @ApiOperation(value = "Obtiene el valor de la tarifa regular",
	            notes = "💰 Obtiene el valor de la tarifa regular de la última tarifa registrada")
	    @GetMapping(value = "/valorregular")
	public float obtenerTarifaRegular() {
		return (float) tarifaServicio.findTopByOrderByIdTarifaDesc().getTarifaRegular();
	}

	  @ApiOperation(value = "Obtiene el valor de la tarifa de pausa",
	            notes = "⏸️ Obtiene el valor de la tarifa de pausa de la última tarifa registrada")
	    @GetMapping(value = "/valorpausa")
	public float obtenerTarifaPausa() {
		return (float) tarifaServicio.findTopByOrderByIdTarifaDesc().getTarifaPausa();
	}

	  @ApiOperation(value = "Elimina una tarifa",
	            notes = "❌ Elimina una tarifa del historial según su ID")
	    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "✅ Tarifa borrada exitosamente"),
	            @ApiResponse(code = 400, message = "❌ No se pudo borrar la tarifa"),
	            @ApiResponse(code = 401, message = "❌ No autorizado para realizar esta acción")
	    })
	    @DeleteMapping(value = "/borrar/{id}")
	public ResponseEntity<String> borrar(@PathVariable int id, @RequestHeader("Authorization") String token) {
		if (JWTAuthorizationFilter.verificarTokenContieneAutorizacion(token, "ROLE_ADMIN"))
			if (tarifaServicio.delete(id))
				return new ResponseEntity<>("Borrado id: " + id, HttpStatus.OK);
			else
				return new ResponseEntity<>("No borrado", HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}
}
