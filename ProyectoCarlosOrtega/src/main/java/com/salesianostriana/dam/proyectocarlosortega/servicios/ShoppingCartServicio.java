package com.salesianostriana.dam.proyectocarlosortega.servicios;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.salesianostriana.dam.proyectocarlosortega.model.LineadeVenta;
import com.salesianostriana.dam.proyectocarlosortega.model.Producto;
import com.salesianostriana.dam.proyectocarlosortega.model.Venta;
import com.salesianostriana.dam.proyectocarlosortega.repositorio.ProductoRepositorio;
import com.salesianostriana.dam.proyectocarlosortega.security.Usuario;
import com.salesianostriana.dam.proyectocarlosortega.security.UsuarioRepo;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCartServicio {

	@Autowired
	private ProductoRepositorio productoRepository;
	
	@Autowired
	private UsuarioRepo usuarioRepo;

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
			products.replace(p, products.get(p) + 1);
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

	public void enviarVenta(UserDetails user) {


		if (!products.isEmpty()) {
			
			LineadeVenta lv;
			Venta venta;

			
			Optional<Usuario> usuario = usuarioRepo.findUserByUsername(user.getUsername());
			
			if (usuario.isEmpty())
				return; // By LuisMi. Ángel, perdona a Carlos que no sabe lo que hace XD
			
			List<LineadeVenta> listaLineadeVenta = new ArrayList<LineadeVenta>();
			venta = Venta.builder()
					.fechaVenta(LocalDate.now())
					.fechaEntrega(LocalDate.now().plusDays(5))
					.entrega(false)
					.user(user.getUsername())
					.direccion(usuario.get().getDireccion())
					.build();
			

			
			ventaServicio.save(venta);


			for (Map.Entry<Producto, Integer> carrito : products.entrySet()) {

				lv = LineadeVenta.builder().producto(carrito.getKey()).cantidad(carrito.getValue())
						.precio(carrito.getValue() * carrito.getKey().getPrecio()).build();
				listaLineadeVenta.add(lv);

				if (!listaLineadeVenta.isEmpty()) {
					for (LineadeVenta lineadeVenta : listaLineadeVenta) {

						lineadeVenta.addToVenta(venta);
						lineaDeventaServicio.save(lineadeVenta);

					}

				}

			}
			//Si el pedido se hace un lunes la fecha de entrega se acorta 
			//y se hace un descuento
			venta.setTotal(ventaServicio.calcularPrecioTotalIva());
			if (venta.getFechaEntrega().getDayOfWeek() == DayOfWeek.MONDAY) {
				venta.getFechaEntrega().minusDays(2);
				venta.setTotal(ventaServicio.calcularOfertaSuperLunes());
			}
			//Si la entrega no ha sido efectuada en el dia concretado se hara un 50% de descuento
			if(venta.getFechaEntrega().isBefore(LocalDate.now()) && venta.getEntrega()==false) {
				venta.setTotal(ventaServicio.calcularEntregaAtrasada());
			}
			ventaServicio.save(venta);
		}
		
		
		products.clear();

	}
	


}
