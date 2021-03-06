package com.salesianostriana.dam.proyectocarlosortega.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.proyectocarlosortega.security.Usuario;
import com.salesianostriana.dam.proyectocarlosortega.security.UsuarioRepo;



@Controller
public class AdminController {

	@Autowired
	private UsuarioRepo usuarioRepo;
	
    @GetMapping("/admin")
    public String adminIndex(Model model, @AuthenticationPrincipal UserDetails user) {
       
    	Optional<Usuario> elUsuario = usuarioRepo.findUserByUsername(user.getUsername());

    	model.addAttribute("usuario", elUsuario.get());
        return "index";
    }
}
