package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Parada;

public interface ParadaRepository extends JpaRepository<Parada,Long>{

	
	@Qualifier("paradaRepository")
	@Autowired
	
	public Parada findById(int id);

}
