package uy.edu.ort.obligatorio.pizzahurt.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.MedioDePago;
import uy.edu.ort.obligatorio.pizzahurt.constraints.MedioPago;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Usuario;
import uy.edu.ort.obligatorio.pizzahurt.service.MediodepagoService;

import java.text.ParseException;


@Controller
@SessionAttributes("usuario")
@AllArgsConstructor
@RequestMapping("/medios-de-pago")
public class MedioDePagoController {

    private MediodepagoService mediodepagoService;


    @GetMapping("/nuevo")
    public String index(Model model) {

        if(!model.containsAttribute("medioDePago"))
            model.addAttribute("medioDePago", new MedioDePago());

        return "medio-de-pago";
    }

    @GetMapping("/listar")
    public String listar(Model model,@AuthenticationPrincipal Usuario usuario) {

        model.addAttribute("mediosDePago",mediodepagoService.getAllMediodepagos(usuario));
        model.addAttribute("medioDePago",new MedioDePago());
        return "table-medios-de-pago";
    }

    @PostMapping("/nuevo")
    public String nuevaMedioPago(@Validated(MedioPago.class) @ModelAttribute MedioDePago medioDePago, BindingResult result, Model model,@AuthenticationPrincipal Usuario usuario) throws ParseException {

        if(result.hasErrors()){
            model.addAttribute("medioDePago", medioDePago);
            return "medio-de-pago";
        }

        medioDePago.setUsuario(usuario);
        mediodepagoService.createMediodepago(medioDePago);
        return "redirect:/medios-de-pago/listar";
    }

    @PostMapping("/modificar/{id}")
    public String modificarMedioPago(@PathVariable("id") MedioDePago medioDePago, Model model) {
        model.addAttribute("medioDePago",medioDePago);
        return "medio-de-pago";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarMedioPago(@PathVariable("id") MedioDePago medioDePago) {
        mediodepagoService.deleteMediodepago(medioDePago);
        return "redirect:/medios-de-pago/listar";
    }


}
