package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.dtos.ReporteViajeIdCountMonopatinDto;
import com.example.demo.model.Viaje;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long> {

	@Qualifier("viajeRepository")
	@Autowired

	public List<Viaje> findAll();

	public Viaje findById(int id);

	@Query("SELECT new com.example.demo.dtos.ReporteViajeIdCountMonopatinDto(v.monopatin.patente, COUNT(v.monopatin))"
			+ " from Viaje v" + " WHERE YEAR(v.fechaInicio) = :anio GROUP BY v.monopatin"
			+ " HAVING COUNT(v.monopatin)>:cantidad")
	public List<ReporteViajeIdCountMonopatinDto> obtenerReportePorAnioConCantidadMinima(
			@Param("cantidad") long cantidad, @Param("anio") Integer anio);

	@Query("SELECT SUM(v.precioFinal) from Viaje v WHERE v.fechaInicio BETWEEN :fecha1 AND :fecha2")
	public List<String> reporteFacturadoEntreDosFechas(@Param("fecha1") LocalDate fecha1,
			@Param("fecha2") LocalDate fecha2);
}

/* WHERE MONTH(v.fechaInicio) BETWEEN MONTH(01) AND MONTH(10) */
