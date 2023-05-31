package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.CuentaMercadoPago;

public interface CuentaMercadoPagoRepository extends JpaRepository<CuentaMercadoPago, Long> {

	public CuentaMercadoPago findById(int id);

}
