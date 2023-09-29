package uy.edu.ort.obligatorio.pizzahurt.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Mediodepago;
import uy.edu.ort.obligatorio.pizzahurt.service.MediodepagoService;

@Controller
@SessionAttributes("usuario")
@AllArgsConstructor
@RequestMapping("medios-de-pago")
public class MedioDePagoController {

    private MediodepagoService mediodepagoService;


    @GetMapping("/nueva")
    public String index(Model model) {

        if(!model.containsAttribute("medio-de-pago"))
            model.addAttribute("medio-de-pago", new Mediodepago());

        return "medio-de-pago";
    }
}
