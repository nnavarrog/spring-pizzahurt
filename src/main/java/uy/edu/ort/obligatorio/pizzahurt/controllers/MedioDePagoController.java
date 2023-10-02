package uy.edu.ort.obligatorio.pizzahurt.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.MedioDePago;
import uy.edu.ort.obligatorio.pizzahurt.service.MediodepagoService;

@Controller
@SessionAttributes("usuario")
@AllArgsConstructor
@RequestMapping("/medios-de-pago")
public class MedioDePagoController {

    private MediodepagoService mediodepagoService;


    @GetMapping("/nuevo")
    public String index(Model model) {

        if(!model.containsAttribute("medio-de-pago"))
            model.addAttribute("medio-de-pago", new MedioDePago());

        return "medio-de-pago";
    }

    @GetMapping("/listar")
    public String listar(Model model) {

        model.addAttribute("mediosDePago",mediodepagoService.getAllMediodepagos());
        model.addAttribute("medio-de-pago",new MedioDePago());
        return "table-medios-de-pago";
    }

    @PostMapping("/nueva")
    public String nuevaCreacion(@Valid @ModelAttribute MedioDePago mediodepago, BindingResult result, Model model){

        if(result.hasErrors()){
            model.addAttribute("medio-de-pago", mediodepago);
            return "medio-de-pago";
        }

        mediodepagoService.createMediodepago(mediodepago);
        return "redirect:/creaciones/listar";
    }

    @PostMapping("/modificar/{id}")
    public String modificarCreacion(@PathVariable("id") MedioDePago mediodepago, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("medio-de-pago", mediodepago);
        return "redirect:/medio-de-pago/nueva";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarCreacion(@PathVariable("id") MedioDePago mediodepago) {
        mediodepagoService.deleteMediodepago(mediodepago);
        return "redirect:/medio-de-pago/listar";
    }


}
