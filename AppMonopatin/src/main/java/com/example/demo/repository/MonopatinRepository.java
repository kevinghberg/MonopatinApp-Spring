package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Monopatin;

@Repository
public interface MonopatinRepository extends JpaRepository<Monopatin, Long> {

	public Monopatin findByPatente(String patente);

	public Monopatin findByIdMonopatin(int idMonopatin);

	public Monopatin findById(int id);
}
