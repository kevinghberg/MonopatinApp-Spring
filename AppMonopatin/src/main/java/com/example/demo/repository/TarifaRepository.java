package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Tarifa;

@Repository
public interface TarifaRepository extends JpaRepository<Tarifa, Long> {

	public Tarifa findByIdTarifa(int id);
		
	public List<Tarifa> findAll();

	public Tarifa findTopByOrderByIdTarifaDesc();

}
