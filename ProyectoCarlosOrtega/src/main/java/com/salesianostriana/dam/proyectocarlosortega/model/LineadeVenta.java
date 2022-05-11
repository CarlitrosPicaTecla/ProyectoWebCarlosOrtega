package com.salesianostriana.dam.proyectocarlosortega.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data 
@AllArgsConstructor 
@NoArgsConstructor 
@Entity 
@Builder
public class LineadeVenta {
	
	@Id @GeneratedValue
	private long id;

	@ManyToOne
	private Producto producto;
	
	private int cantidad;
	private Double precio;
	private double descuento;
	@ManyToOne
	private Venta venta;
	
	public void addToVenta(Venta venta) {
		this.venta = venta;
		venta.getProductos().add(this);
	}
	
	public void removeFromCurso(Venta venta) {
		venta.getProductos().remove(this);
	}
	
}
