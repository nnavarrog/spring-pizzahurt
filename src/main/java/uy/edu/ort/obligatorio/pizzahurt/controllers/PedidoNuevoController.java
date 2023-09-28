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

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uy.edu.ort.obligatorio.pizzahurt.exceptions.EntidadNoExiste;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.EstadoPedido;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Item;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Pedido;
import uy.edu.ort.obligatorio.pizzahurt.repository.CreacionRepository;
import uy.edu.ort.obligatorio.pizzahurt.repository.TamanioRepository;
import uy.edu.ort.obligatorio.pizzahurt.repository.UsuarioRepository;
import uy.edu.ort.obligatorio.pizzahurt.service.PedidoService;

@Controller
@RequestMapping("/pedidos")
@SessionAttributes("pedidonuevo")
@AllArgsConstructor
public class PedidoNuevoController
{

    private PedidoService pedidoService;
    private CreacionRepository creacionRepo;
    private TamanioRepository tamanioRepo;
    private UsuarioRepository userRepo;

    @GetMapping("/nuevo")
    public String nuevoPedido(Model model,
             @ModelAttribute("pedidonuevo") Pedido pedidoNuevo,
             @ModelAttribute("errors") String erros,
             Authentication auth)
    {
        pedidoNuevo.updateAmounts();
        //Usuario user = (Usuario) auth.getPrincipal();
        model.addAttribute("pedidonuevo", pedidoNuevo);
        model.addAttribute("tamanios", tamanioRepo.findAll());
        model.addAttribute("creaciones", creacionRepo.findAll()/*user.getCreaciones()*/);
        model.addAttribute("item", Item.builder().build());
        return "pedido-nuevo";
    }

    @PostMapping("/additem")
    public String addItem(Model model,
             @ModelAttribute Item item,
             @ModelAttribute("pedidonuevo") Pedido pedidoNuevo,
             Authentication auth,
             RedirectAttributes redirectAttrtibuttes)
    {
        try
        {
            pedidoNuevo = pedidoService.addItemToPedido(pedidoNuevo, item);
        }
        catch (EntidadNoExiste ex)
        {
            Logger.getLogger(PedidoNuevoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/pedidos/nuevo";
    }

    @ModelAttribute("pedidonuevo")
    public Pedido pedidoNuevo(Authentication auth)
    {
        Pedido pedidoNuevo = Pedido.builder()
                .created(new Date())
                .lastUpdate(new Date())
                .estado(EstadoPedido.NUEVO)
                .user(userRepo.findAll().get(0))
                .build();
        return pedidoNuevo;
    }
}
