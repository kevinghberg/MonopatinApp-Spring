package com.example.demo.controller;

import java.util.List;
import java.util.ArrayList;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.security.JWTAuthorizationFilter;

import com.example.demo.dtos.MonopatinEstadoDto;
import com.example.demo.dtos.ReporteMonopatinDto;
import com.example.demo.model.Monopatin;
import com.example.demo.model.Parada;
import com.example.demo.services.MonopatinServicio;
import com.example.demo.services.ParadaServicio;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("monopatines")
@Api(tags = "🛴 Monopatin Controller")
public class MonopatinController {

	@Qualifier("monopatinServicio")
	@Autowired

	private MonopatinServicio monopatinServicio;
	private ParadaServicio paradaServicio;

	public MonopatinController(@Qualifier("monopatinServicio") MonopatinServicio monopatin,
			@Qualifier("paradaServicio") ParadaServicio paradaServicio) {
		this.paradaServicio = paradaServicio;
		this.monopatinServicio = monopatin;
	}

	@GetMapping("/")
	@ApiOperation(value = "🔍 Obtener lista de monopatines", notes = "Obtiene una lista de todos los monopatines registrados.")
	@ApiResponses(value = 
			{ @ApiResponse(code = 200, message = "Lista de monopatines obtenida exitosamente"),
			  @ApiResponse(code = 401, message = "❌ No autorizado"), })
	public List<Monopatin> getMonopatines() {
		return monopatinServicio.findAll();
	}

	@PostMapping(value = "/agregar", headers = "content-type=application/json")
	@ApiOperation(value = "➕ Agregar monopatín", notes = "Agrega un nuevo monopatín.")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "✔️ Monopatín agregado exitosamente"),
			@ApiResponse(code = 400, message = "❌ Error al agregar el monopatín"),
			@ApiResponse(code = 401, message = "❌ No autorizado") })
	public ResponseEntity<Monopatin> agregarMonopatin(@RequestBody Monopatin monopatin,
			@RequestHeader("Authorization") String token) {
		if (JWTAuthorizationFilter.verificarTokenContieneAutorizacion(token, "ROLE_ADMIN")) {
			if (monopatinServicio.save(monopatin) != null)
				return new ResponseEntity<>(monopatin, HttpStatus.CREATED);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@GetMapping("/obtener/{patente}")
    @ApiOperation(value = "🔍 Obtener monopatín por patente", notes = "Obtiene un monopatín por su número de patente.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Monopatín obtenido exitosamente"),
            @ApiResponse(code = 401, message = "❌ No autorizado"),
            @ApiResponse(code = 404, message = "❌ Monopatín no encontrado"),
    })
	public Monopatin findByPatente(@PathVariable String patente) {
		return monopatinServicio.findByPatente(patente);
	}

	 @DeleteMapping(value = "/borrar/{id}")
	    @ApiOperation(value = "🗑️ Borrar monopatín", notes = "Elimina un monopatín por su ID.")
	    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "✔️ Monopatín borrado exitosamente"),
	            @ApiResponse(code = 400, message = "❌ Error al borrar el monopatín"),
	            @ApiResponse(code = 401, message = "❌ No autorizado")
	    })
	public ResponseEntity<String> borrar(@PathVariable int id, @RequestHeader("Authorization") String token) {
		if (JWTAuthorizationFilter.verificarTokenContieneAutorizacion(token, "ROLE_ADMIN")) {
			if (monopatinServicio.delete(id))
				return new ResponseEntity<>("Borrado id: " + id, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@PutMapping("/mantenimiento")
    @ApiOperation(value = "🔧 Actualizar estado de mantenimiento", notes = "Actualiza el estado de mantenimiento de un monopatín.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "✔️ Estado de mantenimiento actualizado exitosamente"),
            @ApiResponse(code = 400, message = "❌ Error al actualizar el estado de mantenimiento"),
            @ApiResponse(code = 401, message = "❌ No autorizado")
    })
	public ResponseEntity<Monopatin> actualizarMantenimiento(@RequestBody MonopatinEstadoDto mmdto,
			@RequestHeader("Authorization") String token) {
		if (JWTAuthorizationFilter.verificarTokenContieneAutorizacion(token, "ROLE_ADMIN")) {
			Monopatin monopatin = monopatinServicio.actualizarEstadoMantenimiento(mmdto);
			if (monopatin != null)
				return new ResponseEntity<>(monopatin, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

	}

	@PutMapping("/enuso")
    @ApiOperation(value = "✏️ Actualizar estado en uso", notes = "Actualiza el estado de un monopatín a 'En Uso'.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "✔️ Estado de uso actualizado exitosamente"),
            @ApiResponse(code = 400, message = "❌ Error al actualizar el estado de uso"),
            @ApiResponse(code = 401, message = "❌ No autorizado")
    })
	public ResponseEntity<Monopatin> actualizarEstadoEnUso(@RequestBody MonopatinEstadoDto mmdto,
			@RequestHeader("Authorization") String token) {
		if (JWTAuthorizationFilter.verificarTokenContieneAutorizacion(token, "ROLE_ADMIN")) {
			Monopatin monopatin = monopatinServicio.actualizarEstadoEnUso(mmdto);
			if (monopatin != null)
				return new ResponseEntity<>(monopatin, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@GetMapping("/reporte/conpausa")
    @ApiOperation(value = "📊 Obtener reporte con pausa", notes = "Obtiene un reporte de monopatines con pausa.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "✔️ Reporte de monopatines con pausa obtenido exitosamente"),
            @ApiResponse(code = 401, message = "❌ No autorizado")
    })
	public ResponseEntity<List<ReporteMonopatinDto>> obtenerReporteConPausa(
			@RequestHeader("Authorization") String token) {
		if (JWTAuthorizationFilter.verificarTokenContieneAutorizacion(token, "ROLE_ADMIN")) {
			return new ResponseEntity<>(monopatinServicio.obtenerReporteConPausa(), HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

	}

	@GetMapping("/reporte/sinpausa")
    @ApiOperation(value = "📊 Obtener reporte sin pausa", notes = "Obtiene un reporte de monopatines sin pausa.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "✔️ Reporte de monopatines sin pausa obtenido exitosamente"),
            @ApiResponse(code = 401, message = "❌ No autorizado")
    })
	public ResponseEntity<List<ReporteMonopatinDto>> obtenerReporteSinPausa(
			@RequestHeader("Authorization") String token) {
		if (JWTAuthorizationFilter.verificarTokenContieneAutorizacion(token, "ROLE_ADMIN")) {
			return new ResponseEntity<>(monopatinServicio.obtenerReporteSinPausa(), HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@GetMapping("/reporte/kilometros")
    @ApiOperation(value = "📊 Obtener reporte por kilometraje", notes = "Obtiene un reporte de monopatines por kilometraje.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "✔️ Reporte de monopatines por kilometraje obtenido exitosamente"),
            @ApiResponse(code = 401, message = "❌ No autorizado")
    })
	public ResponseEntity<List<ReporteMonopatinDto>> obtenerReportePorKilometraje(
			@RequestHeader("Authorization") String token) {
		if (JWTAuthorizationFilter.verificarTokenContieneAutorizacion(token, "ROLE_ADMIN")) {
			return new ResponseEntity<>(monopatinServicio.obtenerReportePorKilometraje(), HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	 @GetMapping(value = "/buscarpordistancia/{latitud}/{longitud}/{distancia}")
	    @ApiOperation(value = "🔍 Buscar monopatines cercanos", notes = "Obtiene una lista de monopatines cercanos a una ubicación específica.")
	    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Lista de monopatines cercanos obtenida exitosamente"),
	            @ApiResponse(code = 401, message = "❌ No autorizado")
	    })
	public List<Monopatin> obtenerCercanas(@PathVariable Double latitud, @PathVariable Double longitud,
			@PathVariable double distancia) {
		List<Parada> listaParadas = paradaServicio.obtenerParadasCercanas(longitud, latitud, distancia);
		List<Monopatin> listaMonopatines = new ArrayList<Monopatin>();
		for (Parada parada : listaParadas) {
			for (Monopatin monopatin : parada.getListaMonopatines()) {
				if (!monopatin.isEnUso() && !monopatin.isEstadoMantenimiento()) {
					listaMonopatines.add(monopatin);
				}
			}
		}
		return listaMonopatines;
	}

}