package com.salesianostriana.dam.proyectocarlosortega.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
@Entity 
@Builder
public class Producto {


	@Id 
	@GeneratedValue
	private long id;
	
	private String nombre;
	private String marca;
	private String descripcion;
	private String tipo;
	private Double precioBase;

}
