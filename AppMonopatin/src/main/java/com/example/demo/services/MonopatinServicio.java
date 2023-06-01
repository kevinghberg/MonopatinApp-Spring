package com.example.demo.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.model.Monopatin;
import com.example.demo.repository.MonopatinRepository;

@Service
public class MonopatinServicio {

	private MonopatinRepository repository;

	public MonopatinServicio(@Qualifier("repository") MonopatinRepository repository) {
		this.repository = repository;
	}

	public Monopatin buscarMonopatinPorId(int idMonopatin) {
		return repository.findByIdMonopatin(idMonopatin);
	}

	public Monopatin findByIdMonopatin(int idMonopatin) {
		// TODO Auto-generated method stub
		return null;
	}

}
