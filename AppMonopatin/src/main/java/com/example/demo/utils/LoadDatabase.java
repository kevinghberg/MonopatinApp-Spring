package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.dtos.ParadaMonopatinDto;
import com.example.demo.dtos.ReporteDobleIntDto;
import com.example.demo.dtos.ReporteMonopatinDto;
import com.example.demo.dtos.ReporteViajeIdCountMonopatinDto;
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
			/*vs.registrarLlegada(1);*/
			
			// 3.a Como encargado de mantenimiento quiero poder generar un reporte de uso de
			// monopatines por kilómetros para establecer si un monopatín requiere
			// demantenimiento. Este reporte debe poder configurarse para incluir (o no)
			// lostiempos de pausa

			List<ReporteMonopatinDto> listaPorKm = ms.obtenerReportePorKilometraje();
			for (ReporteMonopatinDto reporte : listaPorKm) {
				System.out.println("3.a patente monopatin: " + reporte.getPatente());
				System.out.println("3.a kilometraje: " + reporte.getValor());
			}

			long cantidad = 0;
			Integer anio = 2023;
			List<ReporteViajeIdCountMonopatinDto> lista = vs.obtenerReportePorAnioConCantidadMinima(cantidad, anio);
			for (ReporteViajeIdCountMonopatinDto reporte : lista) {
				System.out.println("3.c patente monopatin: " + reporte.getPatente());
				System.out.println("3.c cantidad viajes: " + reporte.getCount());
			}

			// 3.d Como administrador quiero consultar el total facturado en un rango de
			// meses de cierto año.
			int anioReporte = 2023;
			int mes1 = 1;
			int mes2 = 10;
			LocalDate fecha1 = LocalDate.of(anioReporte, mes1, 1);
			LocalDate fecha2 = LocalDate.of(anioReporte, mes2, 31);
			List<String> lista2 = vs.reporteFacturadoEntreDosFechas(fecha1, fecha2);
			for (String reporte : lista2)
				System.out.println("3.d total facturado: " + reporte);

			// 3.e Como administrador quiero consultar la cantidad de monopatines actualmente
			// en operación, versus la cantidad de monopatines actualmente en mantenimiento.
			ReporteDobleIntDto reporteEnUsoVsMantenimiento = ms.obtenerReporteEnUsoVsEnMantenimiento();
			System.out.println("3.e monopatines en uso: " + reporteEnUsoVsMantenimiento.getValor1());
			System.out.println("3.e monopatines en mantenimiento: " + reporteEnUsoVsMantenimiento.getValor2());

			// 3. g Como usuario quiero lun listado de los monopatines cercanos a mi zona,
			// para poder encontrar un monopatín cerca de mi ubicación

			List<Parada> paradasCercanas = ps.obtenerParadasCercanas(-38.57833280017979, -58.728512261665905, 1000);
			for (Parada parada : paradasCercanas) {
				System.out.println("3.g: id parada: " + parada.getIdParada());
			}

		};
	}
}