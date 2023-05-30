package com.example.demo.model;

import java.util.List;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUsuario;

	@Column
	private String nombre;
	
	@Column
	private String apellido;
	
	@Column
	private String celular;
	
	@Column(unique=true)
	private String email;
	
	@Column
	private boolean estadoCuentaAnulada;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name="relacion_usuario_mp",joinColumns = {
            @JoinColumn(name = "usuario_id", referencedColumnName = "id",
                    nullable = false, updatable = false)},
    inverseJoinColumns = {
            @JoinColumn(name = "mp_id", referencedColumnName = "id",
                    nullable = false, updatable = false)})
	private List<CuentaMercadoPago> listaMP;

	public Usuario() {
		super();
	}

	public Usuario(String nombre, String apellido, String celular, String email,
			boolean estadoCuentaAnulada) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.celular = celular;
		this.email = email;
		this.estadoCuentaAnulada = estadoCuentaAnulada;
	}
	
}
