package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dtos.ParadaMonopatinDto;
import com.example.demo.dtos.ViajeMonopatinUsuarioDto;
import com.example.demo.model.CuentaMercadoPago;
import com.example.demo.model.Usuario;
import com.example.demo.model.Viaje;
import com.example.demo.security.JWTAuthorizationFilter;
import com.example.demo.services.ParadaServicio;
import com.example.demo.services.ViajeServicio;

@Configuration
@RestController
@RequestMapping("viajes")
public class ViajeController {

	@Qualifier("viajeServicio")
	@Autowired
	
	private ViajeServicio viajeServicio;
	private ParadaServicio paradaServicio;

	@Bean
	RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Autowired
	private RestTemplate restTemplate;

	static final String USUARIOS_URL = "http://localhost:8081/usuarios/";
	static final String CUENTASMP_URL = "http://localhost:8081/cuentasmp/";



	public ViajeController(@Qualifier("viajeServicio") ViajeServicio viajeServicio,
			@Qualifier("paradaServicio") ParadaServicio paradaServicio) {
		this.paradaServicio = paradaServicio;
		this.viajeServicio = viajeServicio;
	}

	@GetMapping("/")
	public ResponseEntity<List<Viaje>> getViajes(@RequestHeader("Authorization") String token) {
		if (JWTAuthorizationFilter.verificarTokenContieneAutorizacion(token, "ROLE_ADMIN"))
			return new ResponseEntity<>(viajeServicio.findAll(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@PostMapping(value = "/registrarviaje", headers = "content-type=application/json")
	public ResponseEntity<Viaje> registrarViaje(@RequestBody ViajeMonopatinUsuarioDto vmudto,
			@RequestHeader("Authorization") String token) {
		Usuario usuario = restTemplate
				.exchange(USUARIOS_URL + "obtener/id/" + vmudto.getIdUsuario(), HttpMethod.GET, null, Usuario.class)
				.getBody();
		if (JWTAuthorizationFilter.verificarTokenContieneAutorizacion(token, usuario.getEmail())) {
			if (!usuario.isEstadoCuentaAnulada() && !usuario.isEnViaje()) {
				double saldoMaximo = restTemplate.exchange(USUARIOS_URL + "saldo_maximo/" + usuario.getIdUsuario(),
						HttpMethod.GET, null, Integer.class).getBody();
				Viaje viaje = viajeServicio.registrarViaje(vmudto, saldoMaximo);
				System.out.println(viaje);
				if (viaje != null) {
					paradaServicio.agregarRelacionMonopatin(
							new ParadaMonopatinDto(vmudto.getIdMonopatin(), vmudto.getIdParadaComienzo()));
					return new ResponseEntity<>(viaje, HttpStatus.OK);
				} else
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			} else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

	}

	@PutMapping(value = "registrarllegada/{id}")
	public ResponseEntity<Viaje> registrarLlegada(@PathVariable int id) {
		Viaje viaje = viajeServicio.registrarLlegada(id);
		if (viaje != null) {
			int id_usuario = viaje.getIdUsuario();
			double precioFinal = viaje.getPrecioFinal();
			/*restTemplate.exchange(USUARIOS_URL + "enviaje/" + viaje.getIdUsuario() + "/" + false, HttpMethod.PUT, null,
					boolean.class);*/
			/*restTemplate.exchange(CUENTASMP_URL + "debitar_dinero/" + id_usuario + "/" + precioFinal, HttpMethod.PUT,
					null, CuentaMercadoPago.class);*/
			return new ResponseEntity<>(viaje, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(value = "/borrar/{id}")
	public ResponseEntity<String> borrar(@PathVariable int id) {
		if (viajeServicio.delete(id)) {
			return new ResponseEntity<String>("Borrado" + id, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No borrado", HttpStatus.BAD_REQUEST);
		}

	}
}
