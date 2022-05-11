package com.salesianostriana.dam.proyectocarlosortega.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.proyectocarlosortega.base.BaseServicelmpl;
import com.salesianostriana.dam.proyectocarlosortega.model.Producto;
import com.salesianostriana.dam.proyectocarlosortega.repositorio.ProductoRepository;
@Service
public class ProductoServicio 
	extends BaseServicelmpl<Producto, Long, ProductoRepository>  {

	// Inyectamos la dependencia al nuevo estilo, sin @Autowired
	private ProductoRepository productoRepository;
	
	public ProductoServicio(ProductoRepository prepo) {
		this.productoRepository = prepo;
	}
	
	/**
	 * Inserta un nuevo alumno
	 * 
	 * @param a el Alumno a insertar
	 * @return El alumno ya insertado (con el Id no vacío).
	 */
	public Producto add(Producto p) { return productoRepository.save(p); }
	
	
	/**
	 * Edita un alumno, si existe; si no, lo inserta como uno nuevo.
	 * @param a
	 * @return
	 */
	public Producto edit(Producto p) { return productoRepository.save(p); }

	/**
	 * Elimina el alumno
	 * 
	 * @param a
	 */
	public void delete(Producto p) { productoRepository.delete(p); }
	
	/**
	 * Elimina a un alumno por su Id
	 * @param id
	 */
	public void delete(long id) { productoRepository.deleteById(id); }
	
	/**
	 * Devuelve todos los alumnos
	 * @return
	 */
	public List<Producto> findAll() { return productoRepository.findAll(); }
	
	
	/**
	 * Devuelve un alumno en base a su Id
	 * @param id
	 * @return el alumno encontrado o <code>null</code>
	 */
	public Optional<Producto> findById(long id) {
		return productoRepository.findById(id);
	}
}
