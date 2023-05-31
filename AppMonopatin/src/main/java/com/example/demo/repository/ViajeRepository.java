package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Monopatin;
import com.example.demo.model.Viaje;

public interface ViajeRepository extends JpaRepository<Viaje, Long> {

	List<Viaje> findAll();

}
