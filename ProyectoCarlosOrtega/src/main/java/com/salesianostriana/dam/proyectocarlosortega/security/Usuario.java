package com.salesianostriana.dam.proyectocarlosortega.security;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

	private String username, password, role;
	private String nombre, apellidos;
	// private String avatar;
	private LocalDate fechaNacimiento;
	private String direccion;

}
