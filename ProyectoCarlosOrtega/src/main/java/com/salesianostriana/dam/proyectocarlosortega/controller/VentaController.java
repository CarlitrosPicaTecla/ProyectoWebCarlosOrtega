package com.salesianostriana.dam.proyectocarlosortega.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.salesianostriana.dam.proyectocarlosortega.model.Producto;
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
    
    
    public String totalVenta () {
    
    	ventaServicio.calcularPrecioTotalIva();
    	
    	return "redirect:/private/index";
    
    }
    
    
    
}
