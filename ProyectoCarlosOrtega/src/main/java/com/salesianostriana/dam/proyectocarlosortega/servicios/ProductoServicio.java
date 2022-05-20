package com.salesianostriana.dam.proyectocarlosortega.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.proyectocarlosortega.base.BaseServicelmpl;
import com.salesianostriana.dam.proyectocarlosortega.model.Producto;
import com.salesianostriana.dam.proyectocarlosortega.repositorio.ProductoRepositorio;
@Service
public class ProductoServicio 
	extends BaseServicelmpl<Producto, Long, ProductoRepositorio>  {

	// Inyectamos la dependencia al nuevo estilo, sin @Autowired
	private ProductoRepositorio productoRepository;
	
	public ProductoServicio(ProductoRepositorio prepo) {
		this.productoRepository = prepo;
	}
	

	public Producto add(Producto p) { return productoRepository.save(p); }
	
	

	public Producto edit(Producto p) { return productoRepository.save(p); }


	public void delete(Producto p) { productoRepository.delete(p); }
	

	public void delete(long id) { productoRepository.deleteById(id); }
	

	public List<Producto> findAll() { return productoRepository.findAll(); }
	
	

	public Optional<Producto> findById(long id) {
		return productoRepository.findById(id);
	}
	
	public List<Producto> buscarPorNombre(String cadena) {
		return productoRepository.findByNombreContainsIgnoreCase(cadena);
	}
	
	
	
}
