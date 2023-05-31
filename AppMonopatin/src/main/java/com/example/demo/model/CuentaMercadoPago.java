package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class CuentaMercadoPago {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private double saldo;
		
	@Column
	private LocalDate fechaAlta;
	
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
	private List<Usuario> listaUsuarios;
    
    
    // In Cuentas class: customer

    @OneToMany(targetEntity=com.example.demo.model.Usuario.class, 
                mappedBy="cuentasMP")
    public Set getOrders() { return orders; }

    // In usuario class: order

    @ManyToOne
    @JoinColumn(name="usuario_id", nullable=false)
    public Usuario getUsuario() { return usuario; }
    
    
    
 
	public CuentaMercadoPago() {
	}

	public CuentaMercadoPago(double saldo) {
		this.saldo = saldo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	
	
	
}
