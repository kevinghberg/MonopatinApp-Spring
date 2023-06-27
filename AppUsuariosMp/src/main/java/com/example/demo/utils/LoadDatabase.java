package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.model.CuentaMercadoPago;
import com.example.demo.model.Usuario;
import com.example.demo.services.CuentaMercadoPagoServicio;
import com.example.demo.services.UsuarioServicio;

@Configuration
@Slf4j
class LoadDatabase {
	Logger log = Logger.getLogger(LoadDatabase.class.getName());

	@Bean
	CommandLineRunner initDatabase(@Qualifier("cuentaMercadoPagoServicio") CuentaMercadoPagoServicio mps,
			@Qualifier("usuarioServicio") UsuarioServicio us) {
		return args -> {
			us.save(new Usuario("Gustavo","Sierra","2262516630","gustavodavid04@gmail.com"));
			mps.save(new CuentaMercadoPago(10000,"sierragustavo@yahoo.com"));
			mps.agregarRelacionUsuario(1,1);
		};
	}
}