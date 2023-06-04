package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Viaje;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Long> {
	
	@Qualifier("viajeRepository")
	@Autowired

	public List<Viaje> findAll();

	public Viaje findById(int id);
}
