package com.salesianostriana.dam.proyectocarlosortega.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesianostriana.dam.proyectocarlosortega.model.Venta;

public interface VentaRepositorio 
extends JpaRepository <Venta, Long>{

	
	@Query("select v from Venta v where v.user = ?1")
	public List<Venta> obtenerVentas(String cliente); 
}
