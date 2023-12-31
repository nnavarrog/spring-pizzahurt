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

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.*;
import uy.edu.ort.obligatorio.pizzahurt.service.CreacionService;


@Controller
@SessionAttributes("usuario")
@AllArgsConstructor
@RequestMapping("/creaciones")
public class CreacionController {

    private CreacionService creacionService;



    @GetMapping("/nueva")
    public String index(Model model) {

        if(!model.containsAttribute("creacion"))
            model.addAttribute("creacion", new Creacion());

        inicializarListas(model);

        return "creacion";
    }

    @GetMapping
    public String listar(Model model,@AuthenticationPrincipal Usuario usuario) {

        model.addAttribute("creaciones",creacionService.getCreacionesByUsuario(usuario));
        model.addAttribute("creacion",new Creacion());
        return "table-creaciones";
    }

    @PostMapping("/nueva")
    public String nuevaCreacion(@Valid @ModelAttribute Creacion creacion, BindingResult result, Model model, @AuthenticationPrincipal Usuario usuario){

        if(result.hasErrors()){
            model.addAttribute("creacion", creacion);
            inicializarListas(model);
            return "creacion";
        }

        creacion.setUsuario(usuario);
        creacionService.altaCreacion(creacion);
        return "redirect:/creaciones";
    }

    @PostMapping("/modificar/{id}")
    public String modificarCreacion(@PathVariable("id") Creacion creacion, Model model) {
        inicializarListas(model);
        model.addAttribute("creacion",creacion);
        return "creacion";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarCreacion(@PathVariable("id") Creacion creacion) {
        creacionService.eliminarCreacion(creacion);
        return "redirect:/creaciones";
    }

    private void inicializarListas(Model model){
        CreacionService.Listas listas = creacionService.obtenerIngredientesCreacion();
        model.addAttribute("toppings",listas.topinsList());
        model.addAttribute("tipos_de_masas",listas.tipoMasaList());
        model.addAttribute("tipos_de_quesos",listas.tipoQuesoList());
        model.addAttribute("tipos_de_salsas",listas.tipoSalsaList());

    }

}
