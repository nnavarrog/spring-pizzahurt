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
@RequestMapping("creaciones")
public class CreacionController {

    private CreacionService creacionService;



    @GetMapping("/nueva")
    public String index(Model model) {

        if(!model.containsAttribute("creacion"))
            model.addAttribute("creacion", new Creacion());

        CreacionService.Listas listas = creacionService.obtenerIngredientesCreacion();
        model.addAttribute("toppings",listas.topinsList());
        model.addAttribute("tipos_de_masas",listas.tipoMasaList());
        model.addAttribute("tipos_de_quesos",listas.tipoQuesoList());
        model.addAttribute("tipos_de_salsas",listas.tipoSalsaList());

        return "creacion";
    }

    @GetMapping("/listar")
    public String listar(Model model) {

        model.addAttribute("creaciones",creacionService.obtenerCreaciones());
        model.addAttribute("creacion",new Creacion());
        return "table-creaciones";
    }

    @PostMapping("/crear")
    public String nuevaCreacion(@ModelAttribute Creacion creacion){

        creacionService.altaCreacion(creacion);
        return "redirect:/creaciones/listar";
    }

    @PostMapping("/modificar/{id}")
    public String modificarCreacion(@PathVariable("id") Creacion creacion, Model model) {
        index(model.addAttribute(creacion));
         return "creacion";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarCreacion(@PathVariable("id") Creacion creacion) {
        creacionService.eliminarCreacion(creacion);
        return "redirect:/creaciones/listar";
    }

}
