package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Tarifa;

@Repository
public interface TarifaRepository extends JpaRepository<Tarifa, Long> {

	@Qualifier("tarifaRepository")
	@Autowired
	
	public Tarifa findByIdTarifa(int id);
		
	public List<Tarifa> findAll();

	public Tarifa findTopByOrderByIdTarifaDesc();

}
