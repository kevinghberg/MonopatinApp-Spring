package com.example.demo.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Monopatin;

@Repository
public interface MonopatinRepository extends JpaRepository<Monopatin, Long> {

	@Qualifier("monopatinRepository")
	@Autowired

	public Monopatin findByPatente(String patente);

	public Monopatin findByIdMonopatin(int idMonopatin);

	@Query("SELECT COUNT(*) FROM Monopatin m WHERE m.enUso = 1")
	public Integer obtenerReporteEnUso();

	@Query("SELECT COUNT(*) FROM Monopatin m WHERE m.estadoMantenimiento = 1")
	public Integer obtenerReporteMantenimiento();

}
