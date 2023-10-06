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
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Usuario;
import uy.edu.ort.obligatorio.pizzahurt.service.UsuarioService;

/**
 *
 * @author mortuzar
 */
@Controller
@AllArgsConstructor
public class UsuarioController
{

    private UsuarioService usuariosService;

    @GetMapping("/form_registro")
    public String form_registro(Model model)
    {
        model.addAttribute("usuario", new Usuario());
        return "form_registro";
    }

    @PostMapping("form_registro")
    public String registro_usuario(@Valid @ModelAttribute Usuario usuario, BindingResult result, RedirectAttributes redirectAttributes, Model model)
    {

        if (result.hasErrors())
        {
            /*redirectAttributes.addFlashAttribute("msgerror", processErrors(result));*/
            model.addAttribute("usuario", usuario);
            return "form_registro";
        }
        usuariosService.crearUsuario(usuario);

        System.out.println(usuario.toString());
        redirectAttributes.addFlashAttribute("msgsuccess", "Usuario " + usuario.getEmail() + " creado correctamente :D");
        return "redirect:/";
    }

    @GetMapping("/")
    public String home(Model model, Authentication auth)
    {
        String page = "index";
        if (auth != null)
        {
            if (auth.isAuthenticated())
            {
                page = "redirect:/creaciones";
            }
        }
        return page;
    }

    private String processErrors(BindingResult result)
    {
        StringBuilder sb = new StringBuilder();
        result.getAllErrors().forEach(error ->
        {
            sb.append(error.getObjectName())
                    .append(" : ")
                    .append(error.getDefaultMessage())
                    .append(" || ");
        });
        return sb.toString();
    }

}
