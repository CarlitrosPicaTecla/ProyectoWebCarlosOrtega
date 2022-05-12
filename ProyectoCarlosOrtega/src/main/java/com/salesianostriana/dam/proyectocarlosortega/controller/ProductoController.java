package com.salesianostriana.dam.proyectocarlosortega.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.proyectocarlosortega.model.Producto;
import com.salesianostriana.dam.proyectocarlosortega.servicios.ProductoServicio;

@Controller
public class ProductoController {

	@Autowired
	private ProductoServicio ps;
	

	@GetMapping ("/productos")
	public String controladorCondicionales (Model model){


		
		model.addAttribute("productosLista", ps.findAll()  );
		return "productos";//Se devuelve la plantilla en HTML
	}
	@GetMapping ("/")
	public String controladorInicio (Model model){

		
		return "index";//Se devuelve la plantilla en HTML
	}

	
	@GetMapping("/nuevo")
	public String mostrarFormulario(Model model) {
		model.addAttribute("producto", new Producto());
		return "formulario";
	}
	
	@PostMapping("/nuevo/submit")
	public String procesarFormulario(@ModelAttribute("producto") Producto p) {
		ps.add(p);
		return "redirect:/productos";//Podría ser también return "redirect:/list
	}
	
	@GetMapping("/borrar/{id}")
	public String borrar(@PathVariable("id") long id) {
		ps.delete(id);
		return "redirect:/productos";
	}
	
	@GetMapping("/editar/{id}")
	public String mostrarFormularioEdicion(@PathVariable("id") long id, Model model) {
		
		//Buscamos al alumno por id y recordemos que el método findById del servicio, devuelve el objeto buscado o null si no se encuentra.
		 
		
		Optional<Producto> aEditar= ps.findById(id);
		System.out.println(aEditar);
		if (aEditar != null) {
			model.addAttribute("producto", aEditar.get());
			return "formulario";
		} else {
			// No existe ningún alumno con el Id proporcionado.
			// Redirigimos hacia el listado.
			return "redirect:/productos";
		}
		
		
	}
	
	/**
	 * Método que procesa la respuesta del formulario al editar
	 */
	@PostMapping("/editar/submit")
	public String procesarFormularioEdicion(@ModelAttribute("producto") Producto p) {
		ps.edit(p);
		return "redirect:/productos";//Volvemos a redirigir la listado a través del controller para pintar la lista actualizada con la modificación hecha
	}
	
}
