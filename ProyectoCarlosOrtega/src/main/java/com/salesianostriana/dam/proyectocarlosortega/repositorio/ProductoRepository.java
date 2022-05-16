package com.salesianostriana.dam.proyectocarlosortega.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.proyectocarlosortega.model.Producto;



public interface ProductoRepository 
	extends JpaRepository <Producto, Long> {

	List<Producto> findByNombreContainsIgnoreCase(String nombre);

	
}
