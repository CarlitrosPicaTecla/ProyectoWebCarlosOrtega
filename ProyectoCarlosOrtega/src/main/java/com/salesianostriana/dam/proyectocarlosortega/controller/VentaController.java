package com.salesianostriana.dam.proyectocarlosortega.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.salesianostriana.dam.proyectocarlosortega.model.Producto;
import com.salesianostriana.dam.proyectocarlosortega.model.Venta;
import com.salesianostriana.dam.proyectocarlosortega.security.UsuarioRepo;
import com.salesianostriana.dam.proyectocarlosortega.servicios.ShoppingCartServicio;
import com.salesianostriana.dam.proyectocarlosortega.servicios.VentaServicio;


@Controller
public class VentaController {

	@Autowired
	private ShoppingCartServicio shoppingCartService;


	@Autowired
	private VentaServicio ventaServicio;
	

	
    
    
    
	@GetMapping ("/admin/pedidos")
	public String listaDeVentasAdmin (Model model){


		
		model.addAttribute("ventasLista", ventaServicio.findAll());
		return "pedidos";
	}
	
	
	@GetMapping ("/private/compras")
	public String listaDeVentasCliente (@AuthenticationPrincipal UserDetails user ,Model model){

		
		
		model.addAttribute("comprasLista", ventaServicio.mostrarCompraUsuario(user.getUsername()));
		return "compras";
	}
	
	
	
	@GetMapping("/admin/entregado/{id}")
	public String cambiarEstadoEntrega(@PathVariable("id") long id) {
		
		 
		
		Optional<Venta> aEditar= ventaServicio.findById(id);
		if (aEditar.isPresent()) {
			Venta v = aEditar.get();
			v.setEntrega(true);
			ventaServicio.save(v);
			
		}
		return "redirect:/admin/pedidos";

		
		
	}
    
    
    public String totalVenta () {
    
    	ventaServicio.calcularPrecioTotalIva();
    	
    	return "redirect:/private/index";
    
    }
    
    
    
}
