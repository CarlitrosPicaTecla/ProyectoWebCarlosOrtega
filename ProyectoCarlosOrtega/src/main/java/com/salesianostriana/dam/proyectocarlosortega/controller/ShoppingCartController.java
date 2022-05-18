package com.salesianostriana.dam.proyectocarlosortega.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.salesianostriana.dam.proyectocarlosortega.model.Producto;
import com.salesianostriana.dam.proyectocarlosortega.servicios.ProductoServicio;
import com.salesianostriana.dam.proyectocarlosortega.servicios.ShoppingCartServicio;



@Controller
public class ShoppingCartController {
	
	@Autowired
	private ShoppingCartServicio shoppingCartServicio;
	
	@Autowired
	private ProductoServicio productoServicio;
	
    @Autowired
    public ShoppingCartController(ShoppingCartServicio shoppingCartService, ProductoServicio productService) {
        this.shoppingCartServicio = shoppingCartService;
        this.productoServicio = productService;
    }
    
    @GetMapping ("/private/carrito")
    public String showCarrito (Model model) {
    	
    	if (model.addAttribute("products",shoppingCartServicio.getProductsInCart()) == null)
    		return "redirect:/";
    	return "carrito.html";
    }

    @GetMapping ("/private/productoACarrito/{id}")
    public String productoACarrito (@PathVariable("id") Long id, Model model) {
    	
    	Optional<Producto> opCarrito = productoServicio.findById(id);
    	
    	if(opCarrito.get() != null) {
    		shoppingCartServicio.addProducto(opCarrito.get());
			return "redirect:/private/carrito";
    	}
    	else {
    			return"/private/carrito";
    		}
    	
    	    		 
    }
    
    @GetMapping("/private/borrarProducto/{id}")
    public String removeProductFromCart(@PathVariable("id") Long id) {
    	
    	Optional<Producto> opCarrito = productoServicio.findById(id);
    	
    	if(opCarrito != null) {
    		shoppingCartServicio.removeProducto(opCarrito.get());
    		return "redirect:/private/carrito";
    	}
    	else {
    		return "carrito";

    	}
    }
    
    @ModelAttribute("total_carrito")
    public Double totalCarrito () {
    	
    	Map <Producto,Integer> carrito=shoppingCartServicio.getProductsInCart();
    	double total=0.0;
    	if (carrito !=null) {
        	for (Producto p: carrito.keySet()) {
        		total+=p.getPrecio()*carrito.get(p);
        	}
        	return total;
    	}
    	
    	return 0.0;
    }
    
    
    @ModelAttribute("n_productos")
    public int ContarProductosCarrito(Producto p) {
    	Map <Producto,Integer> carrito=shoppingCartServicio.getProductsInCart();
    	if(carrito!=null) {
    	int cont=0;
    	for (Producto a  : carrito.keySet()) {
			cont+= carrito.get(a);
		}
    	
    	return cont;
    	}
    	
    	return 0;
    }

    @GetMapping ("/private/carrito/enviar")
    public String EnviarVentaCarrito () {
    	shoppingCartServicio.EnviarVenta();
    	
    	return "redirect:/private/index";
    	
    }
    

}
