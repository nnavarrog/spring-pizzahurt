package uy.edu.ort.obligatorio.pizzahurt.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.MedioDePago;
import uy.edu.ort.obligatorio.pizzahurt.constraints.MedioPago;
import uy.edu.ort.obligatorio.pizzahurt.service.MediodepagoService;

@Controller
@SessionAttributes("usuario")
@AllArgsConstructor
@RequestMapping("medios-de-pago")
public class MedioDePagoController {

    private MediodepagoService mediodepagoService;


    @GetMapping("/nuevo")
    public String index(Model model) {

        if(!model.containsAttribute("medio"))
            model.addAttribute("medio", new MedioDePago());

        return "medio-de-pago";
    }

    @GetMapping("/listar")
    public String listar(Model model) {

        model.addAttribute("mediosDePago",mediodepagoService.getAllMediodepagos());
        model.addAttribute("medio",new MedioDePago());
        return "table-medios-de-pago";
    }

    @PostMapping("/nuevo")
    public String nuevaMedioPago(@Validated(MedioPago.class) @ModelAttribute MedioDePago mediodepago, BindingResult result, Model model){

        if(result.hasErrors()){
            model.addAttribute("medio", mediodepago);
            return "medio-de-pago";
        }

        mediodepagoService.createMediodepago(mediodepago);
        return "redirect:/medios-de-pago/listar";
    }

    @PostMapping("/modificar/{id}")
    public String modificarMedioPago(@PathVariable("id") MedioDePago mediodepago, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("medio", mediodepago);
        return "redirect:/medio-de-pago/nuevo";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarMedioPago(@PathVariable("id") MedioDePago mediodepago) {
        mediodepagoService.deleteMediodepago(mediodepago);
        return "redirect:/medio-de-pago/listar";
    }


}
