/**
 * NO LICENCE
 *
 * Proyecto obligatorio final.
 * Curso: Desarrollo de aplicaciones con Spring / Spring Boot
 * Universidad ORT
 * Agosto 2023 - Octubre 2023
 *
 * Docente: Juan Larrayoz
 *
 * Authors:
 *      Fourment, Juan
 *      Navarro Gutérrez, Nicolás
 *      Ortuzar, Martín
 */

package uy.edu.ort.obligatorio.pizzahurt.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uy.edu.ort.obligatorio.pizzahurt.model.dto.NewItemDto;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.*;
import uy.edu.ort.obligatorio.pizzahurt.service.CreacionService;

import java.util.List;

@Controller
@SessionAttributes("usuario")
@AllArgsConstructor
public class CreacionController {

    private CreacionService creacionService;

    @GetMapping("/creacion")
    public String index(Model model) {

        CreacionService.Listas listas = creacionService.obtenerIngredientesCreacion();
        model.addAttribute("toppings",listas.topinsList());
        model.addAttribute("tipos_de_masas",listas.tipoMasaList());
        model.addAttribute("tipos_de_quesos",listas.tipoQuesoList());
        model.addAttribute("tipos_de_salsas",listas.tipoSalsaList());

        return "creacion";
    }

    @PostMapping("creacion/form")
    public String nuevaCreacion(@ModelAttribute Creacion creacion){

        return "redirect:/";
    }

}
