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
	

	
	
	
	
	
	
    @ModelAttribute("total_carrito")
    public Double totalCarrito () {
    	
    	Map <Producto,Integer> carrito=shoppingCartService.getProductsInCart();
    	double total=0.0;
    	if (carrito !=null) {
        	for (Producto p: carrito.keySet()) {
        		total+=p.getPrecio()*carrito.get(p);
        	}
        	return total;
    	}
    	
    	return 0.0;
    }
    
    
    
    @ModelAttribute("carritoConIva")
    public Double totalCarritoConIva () {
    	
    	return ventaServicio.calcularPrecioTotalIva();
    }
    
    
    
	@GetMapping ("/admin/pedidos")
	public String listaDeVentasAdmin (Model model){


		
		model.addAttribute("ventasLista", ventaServicio.findAll()  );
		return "pedidos";
	}
	
	
	@GetMapping ("/private/compras")
	public String listaDeVentasCliente (@AuthenticationPrincipal UserDetails user ,Model model){

		
		
		model.addAttribute("comprasLista", ventaServicio.mostrarCompraUsuario(user.getUsername()));
		return "compras";
	}
	
	
	
	@GetMapping("/admin/entregado/{id}")
	public String cambiarEstadoEntrega(@PathVariable("id") long id) {
		
		//Buscamos al alumno por id y recordemos que el método findById del servicio, devuelve el objeto buscado o null si no se encuentra.
		 
		
		Optional<Venta> aEditar= ventaServicio.findById(id);
		if (aEditar != null) {
			aEditar.get().setEntrega(true);
			
			return "redirect:/admin/pedidos";

			
		}
		return "redirect:/admin/pedidos";

		
		
	}
    
    
    public String totalVenta () {
    
    	ventaServicio.calcularPrecioTotalIva();
    	
    	return "redirect:/private/index";
    
    }
    
    
    
}
