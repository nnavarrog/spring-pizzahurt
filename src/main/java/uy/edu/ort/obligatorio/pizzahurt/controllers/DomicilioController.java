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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.*;
import uy.edu.ort.obligatorio.pizzahurt.service.DomicilioService;


@Controller
@SessionAttributes("usuario")
@AllArgsConstructor
@RequestMapping("/domicilios")
public class DomicilioController {

    private DomicilioService domicilioService;

    @GetMapping("/nueva")
    public String index(Model model) {

        if(!model.containsAttribute("domicilio"))
            model.addAttribute("domicilio", new Domicilio());
        return "domicilio";
    }

    @GetMapping("/listar")
    public String listar(Model model,@AuthenticationPrincipal Usuario usuario){

        model.addAttribute("domicilios",domicilioService.getDomiciliosByUsuario(usuario));
        model.addAttribute("domicilio",new Domicilio());
        return "table-domicilios";
    }

    @PostMapping("/nueva")
    public String nuevoDomicilio(@Valid @ModelAttribute Domicilio domicilio, BindingResult result,@AuthenticationPrincipal Usuario usuario){

        if(result.hasErrors()){
            return "domicilio";
        }

        domicilio.setUsuario(usuario);
        domicilioService.agregarDomicilio(domicilio);
        return "redirect:/domicilios/listar";
    }

    @PostMapping("/modificar/{id}")
    public String modificarDomicilio(@PathVariable("id") Domicilio domicilio, Model model) {
        model.addAttribute("domicilio", domicilio);
        return "domicilio";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarDomicilio(@PathVariable("id") Domicilio domicilio) {
        domicilioService.deleteDomicilio(domicilio.getId());
        return "redirect:/domicilios/listar";
    }
}
