package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.dtos.ParadaMonopatinDto;
import com.example.demo.dtos.RelacionUsuarioMPDto;
import com.example.demo.dtos.ViajeMonopatinUsuarioDto;
import com.example.demo.model.Administrador;
import com.example.demo.model.CuentaMercadoPago;
import com.example.demo.model.Monopatin;
import com.example.demo.model.Parada;
import com.example.demo.model.Tarifa;
import com.example.demo.model.Usuario;
import com.example.demo.services.AdministradorServicio;
import com.example.demo.services.CuentaMercadoPagoServicio;
import com.example.demo.services.MonopatinServicio;
import com.example.demo.services.ParadaServicio;
import com.example.demo.services.TarifaServicio;
import com.example.demo.services.UsuarioServicio;
import com.example.demo.services.ViajeServicio;

@Configuration
@Slf4j
class LoadDatabase {
	Logger log = Logger.getLogger(LoadDatabase.class.getName());

	@Bean
	CommandLineRunner initDatabase(@Qualifier("monopatinServicio") MonopatinServicio ms,
			@Qualifier("administradorServicio") AdministradorServicio as,
			@Qualifier("cuentaMercadoPagoServicio") CuentaMercadoPagoServicio mps,
			@Qualifier("usuarioServicio") UsuarioServicio us, @Qualifier("tarifaServicio") TarifaServicio ts,
			@Qualifier("paradaServicio") ParadaServicio ps, @Qualifier("viajeServicio") ViajeServicio vs) {
		return args -> {
			ms.save(new Monopatin("CGE268")); // AGREGA UN MONOPATIN
			us.save(new Usuario("troesma", "rongeoffry", "2262590909", "prueba@gmail.com")); // AGREGA UN USUARIO
			as.save(new Administrador("Gustavo")); // AGREGA UN ADMINISTRADOR
			mps.save(new CuentaMercadoPago(10000)); // AGREGA UNA CUENTA DE MERCADO PAGO
			ts.save(new Tarifa(2.50, 3.00)); // AGREGA UNA TARIFA
			ps.save(new Parada(-38.574775205044986, -58.72421633509168)); // AGREGA UNA PARADA
			ps.save(new Parada(-38.57461677590632, -58.731129381563456)); // AGREGA UNA PARADA
			mps.agregarRelacionUsuario(new RelacionUsuarioMPDto(1, 1)); // LE ASIGNA UN USUARIO A UNA CUENTA DE MP
			ps.agregarRelacionMonopatin(new ParadaMonopatinDto(1, 1)); // LE ASIGNA UN MONOPATIN A UNA PARADA
			vs.registrarViaje(new ViajeMonopatinUsuarioDto(1, 1, 1, 2));
		};
	}
}