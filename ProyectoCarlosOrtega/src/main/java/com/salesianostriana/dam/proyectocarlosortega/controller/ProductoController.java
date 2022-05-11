package com.salesianostriana.dam.proyectocarlosortega.controller;

import java.util.List;

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

		
		List<Producto> p = 
				List.of( Producto.builder()
				.marca("Risi")
				.nombre("Risketos")
				.tipo("Snack")
				.precioBase(25.0)
				.build(),
				Producto.builder()
				.marca("Risi")
				.nombre("Gusanitos")
				.tipo("Snack")
				.precioBase(20.0)
				.build(),
				Producto.builder()
				.marca("Risi")
				.nombre("MatchBall")
				.tipo("Snack")
				.precioBase(30.0)
				.build(),
				Producto.builder()
				.marca("Fini")
				.nombre("Fresitas")
				.tipo("Gomitas")
				.precioBase(10.0)
				.build(),
				Producto.builder()
				.marca("Trolli")
				.nombre("Arañitas")
				.tipo("Gomitas")
				.precioBase(30.0)
				.build()) ;
		
		model.addAttribute("productosLista", p  );
		return "productos";//Se devuelve la plantilla en HTML
	}
	@GetMapping ("/")
	public String controladorInicio (Model model){

		
		return "index";//Se devuelve la plantilla en HTML
	}
	
	@GetMapping ("/gestion")
	public String controladorGestion (Model model){

		
		return "gestion";//Se devuelve la plantilla en HTML
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
	
}
