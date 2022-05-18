package com.salesianostriana.dam.proyectocarlosortega.servicios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.salesianostriana.dam.proyectocarlosortega.model.LineadeVenta;
import com.salesianostriana.dam.proyectocarlosortega.model.Producto;
import com.salesianostriana.dam.proyectocarlosortega.model.Venta;
import com.salesianostriana.dam.proyectocarlosortega.repositorio.ProductoRepositorio;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCartServicio {

	@Autowired
	private ProductoRepositorio productoRepository;

	@Autowired
	private VentaServicio ventaServicio;

	@Autowired
	private LineadeVentaServicio lineaDeventaServicio;


	private Map<Producto, Integer> products = new HashMap<>();



	/**
	 * Si el producto ya está en el map (en el carrito), solo se incrementará en uno
	 * la cantidad, una unidad más Si el producto no está en el mapa, significa que
	 * no se ha comprado nada de él en este momento, por lo que se añade con
	 * cantidad 1
	 * 
	 * @param producto
	 */

	public void addProducto(Producto p) {
		if (products.containsKey(p)) {
			products.replace(p, products.get(p) + 1);// Ya programamos como "mayores" y podemos poner algún número
														// directamente en el código
		} else {
			products.put(p, 1);
		}
	}

	/**
	 * Método que elimina un producto del carrito. Si en el carrito la cantidad de
	 * dicho producto es más de uno baja la cantidad en una unidad y si es uno
	 * elimina el producto entero
	 * 
	 * @param producto
	 */

	public void removeProducto(Producto p) {
		if (products.containsKey(p)) {
			if (products.get(p) > 1)
				products.replace(p, products.get(p) - 1);
			else if (products.get(p) == 1) {
				products.remove(p);
			}
		}
	}

	/**
	 * @return unmodifiable Copia del carrito no modificable, solo vista
	 */

	public Map<Producto, Integer> getProductsInCart() {
		return Collections.unmodifiableMap(products);
	}

	public void EnviarVenta() {
		LineadeVenta lv;
		Venta venta;
		List<LineadeVenta> listaLineadeVenta = new ArrayList<LineadeVenta>();

		for (Map.Entry<Producto, Integer> carrito : products.entrySet()) {

			lv = LineadeVenta.builder().producto(carrito.getKey()).cantidad(carrito.getValue())
					.precio(carrito.getValue() * carrito.getKey().getPrecio()).build();
			listaLineadeVenta.add(lv);

			venta = Venta.builder().fechaVenta(LocalDate.now()).entrega(false).build();

			if (!listaLineadeVenta.isEmpty()) {
				ventaServicio.save(venta);
				for (LineadeVenta lineadeVenta : listaLineadeVenta) {

					lineadeVenta.addToVenta(venta);
					lineaDeventaServicio.save(lineadeVenta);

				}

				products.clear();

			}
		}
	}

}
