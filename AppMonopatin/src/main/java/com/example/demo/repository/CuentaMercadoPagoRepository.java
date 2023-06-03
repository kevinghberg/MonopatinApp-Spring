package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.CuentaMercadoPago;

public interface CuentaMercadoPagoRepository extends JpaRepository<CuentaMercadoPago, Long> {

	@Qualifier("cuentaMercadoPagoRepository")
	@Autowired
	public CuentaMercadoPago findById(int id);
	
}
