package com.salesianostriana.dam.proyectocarlosortega.servicios;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.proyectocarlosortega.base.BaseServicelmpl;
import com.salesianostriana.dam.proyectocarlosortega.model.Producto;
import com.salesianostriana.dam.proyectocarlosortega.model.Venta;
import com.salesianostriana.dam.proyectocarlosortega.repositorio.VentaRepositorio;

@Service
public class VentaServicio
	extends BaseServicelmpl<Venta, Long, VentaRepositorio>{
	

	@Autowired
	private ShoppingCartServicio shoppingCartServicio;
	@Autowired
	private LineadeVentaServicio lineaVentaServicio;
	
	
	public List<Venta> mostrarCompraUsuario(String nombre){
		
		return repositorio.obtenerVentas(nombre);
		
	}
	
	public double calcularPrecioCarrito(){
		
		double total = 0.0;
		Map<Producto, Integer> carrito = shoppingCartServicio.getProductsInCart();
		
		if(carrito != null) {
			for (Producto p : carrito.keySet()) {
				total+=p.getPrecio()*carrito.get(p);
			}
			
			return total;
		}
		
		
		
		return 0.0;
	}
	
	public double calcularPrecioDescuento() {
		double descuento = 15;
		double total=calcularPrecioCarrito();
		Map<Producto, Integer> carrito = shoppingCartServicio.getProductsInCart();

		if(carrito!=null) {
			if(total>=60) {
				return total-(total*descuento/100);
			}
		}
		
		return total;
	}
	
	public double calcularPrecioTotalIva() {
		double iva = 21;
		double total=calcularPrecioDescuento();
		Map<Producto, Integer> carrito = shoppingCartServicio.getProductsInCart();

		if(carrito!=null) {
			
			return total+(total*iva/100);
			
		}
		
		return 0.0;
	}
	
	
	public double calcularOfertaSuperLunes() {
		
		double descuento= 30;

		double total=calcularPrecioTotalIva();
		Map<Producto, Integer> carrito = shoppingCartServicio.getProductsInCart();

		if(carrito!=null) {
			
			return total-(total*descuento/100);
			
		}
		
		return 0.0;
	}
	
	public double calcularEntregaAtrasada() {
		
		double descuento= 50;

		double total=calcularPrecioTotalIva();
		Map<Producto, Integer> carrito = shoppingCartServicio.getProductsInCart();

		if(carrito!=null) {
			
			return total-(total*descuento/100);
			
		}
		
		return 0.0;
	}
	
	
	


}
