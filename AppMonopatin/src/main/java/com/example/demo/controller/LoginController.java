package com.example.demo.controller;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Administrador;
import com.example.demo.model.Usuario;
import com.example.demo.security.PasswordUtils;
import com.example.demo.services.AdministradorServicio;
import org.springframework.web.client.RestTemplate;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Configuration
@RestController
@RequestMapping("login")
public class LoginController {

	@Qualifier("administradorServicio")
	@Autowired
	private AdministradorServicio administradorServicio;

	public LoginController(@Qualifier("administradorServicio") AdministradorServicio administradorServicio) {
		this.administradorServicio = administradorServicio;
	}

	@Bean
	RestTemplate getRestTemplat() {
		return new RestTemplate();
	}

	@Autowired
	private RestTemplate restTemplate;

	static final String USUARIOS_URL = "http://localhost:8081/usuarios/";

	@GetMapping("/usuario/")
	public String loginUsuario(@RequestParam("email") String email, @RequestParam("contrasena") String contrasena) {
		Usuario user = restTemplate.exchange(USUARIOS_URL + "obtener/email/" + email, HttpMethod.GET, null, Usuario.class).getBody();
		if (user != null && PasswordUtils.checkPassword(contrasena, user.getPassword())) {
			return getJWTToken(email, "ROLE_USER");
		}
		return "Nombre de usuario o contraseña incorrectos.";
	}

	@GetMapping("/admin/")
	public String loginAdmin(@RequestParam("usuario") String usuario, @RequestParam("contrasena") String contrasena) {
		Administrador admin = administradorServicio.findByUsuario(usuario);
		if (admin != null && PasswordUtils.checkPassword(contrasena, admin.getPassword())) {
			return getJWTToken(usuario, "ROLE_ADMIN");
		}
		return "Nombre de usuario o contraseña incorrectos.";
	}

	private String getJWTToken(String username, String rol) {
		String secretKey = "mySecretKey";
		String roles = rol;
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
		String token = Jwts.builder().setId("unused").setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
		return token;
	}
}
