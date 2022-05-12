package com.salesianostriana.dam.proyectocarlosortega.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicController {

	@GetMapping("/inicio")
	public String welcome() {
		return "inicio";
	}


	@GetMapping("/error")
	public String error() {
		return "error";
	}
}
