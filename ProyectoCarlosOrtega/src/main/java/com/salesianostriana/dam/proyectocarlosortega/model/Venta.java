package com.salesianostriana.dam.proyectocarlosortega.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
@Entity 
@Builder
public class Venta {

	@Id @GeneratedValue
	private long idVenta;

	private LocalDate fechaVenta;
	private LocalDate fechaEntrega;
	private Boolean entrega;
	private Double iva;
	private String direccion;
	private Double total;
	private String user;
	
	@Builder.Default
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy="venta", fetch = FetchType.EAGER)
	private List<LineadeVenta> productos = new ArrayList<>();
	
}
