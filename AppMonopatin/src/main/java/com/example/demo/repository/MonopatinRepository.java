package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CuentaMercadoPago;
import com.example.demo.model.Monopatin;

@Repository
public interface MonopatinRepository extends JpaRepository<Monopatin, Long> {
	
	@Qualifier("monopatinRepository")
	@Autowired

	public Monopatin findByPatente(String patente);

	public Monopatin findByIdMonopatin(int idMonopatin);

}
