package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.dtos.ParadaMonopatinDto;
import com.example.demo.model.Administrador;
import com.example.demo.model.Monopatin;
import com.example.demo.model.Parada;
import com.example.demo.model.Tarifa;
import com.example.demo.services.AdministradorServicio;
import com.example.demo.services.MonopatinServicio;
import com.example.demo.services.ParadaServicio;
import com.example.demo.services.TarifaServicio;
import com.example.demo.services.ViajeServicio;

@Configuration
@Slf4j
class LoadDatabase {
	Logger log = Logger.getLogger(LoadDatabase.class.getName());

	@Bean
	CommandLineRunner initDatabase(@Qualifier("monopatinServicio") MonopatinServicio ms,
			@Qualifier("administradorServicio") AdministradorServicio as,
			@Qualifier("tarifaServicio") TarifaServicio ts,
			@Qualifier("paradaServicio") ParadaServicio ps, @Qualifier("viajeServicio") ViajeServicio vs) {
		return args -> {
			as.save(new Administrador("sierragustavo","1234")); // AGREGA UN ADMINISTRADOR
			ms.save(new Monopatin("CGE268")); // AGREGA UN MONOPATIN
			ts.save(new Tarifa(2.50, 3.00)); // AGREGA UNA TARIFA
			ps.save(new Parada(-38.574775205044986, -58.72421633509168)); // AGREGA UNA PARADA
			ps.save(new Parada(-38.57461677590632, -58.731129381563456)); // AGREGA UNA PARADA
			ps.agregarRelacionMonopatin(new ParadaMonopatinDto(1, 1)); // LE ASIGNA UN MONOPATIN A UNA PARADA
			ms.save(new Monopatin("IJB348"));
		};
	}
}