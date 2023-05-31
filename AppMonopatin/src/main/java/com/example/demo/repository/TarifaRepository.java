package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Tarifa;

public interface TarifaRepository extends JpaRepository<Tarifa, Long> {

}
