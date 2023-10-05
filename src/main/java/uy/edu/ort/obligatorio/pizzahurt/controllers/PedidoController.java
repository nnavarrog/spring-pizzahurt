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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uy.edu.ort.obligatorio.pizzahurt.exceptions.EntidadNoExiste;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Domicilio;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.EstadoPedido;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Item;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Pedido;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Usuario;
import uy.edu.ort.obligatorio.pizzahurt.repository.TamanioRepository;
import uy.edu.ort.obligatorio.pizzahurt.service.CreacionService;
import uy.edu.ort.obligatorio.pizzahurt.service.DomicilioService;
import uy.edu.ort.obligatorio.pizzahurt.service.MediodepagoService;
import uy.edu.ort.obligatorio.pizzahurt.service.PedidoService;
import uy.edu.ort.obligatorio.pizzahurt.utils.StringUtil;

@Controller
@RequestMapping("/pedidos")
@SessionAttributes("pedidonuevo")
@AllArgsConstructor
public class PedidoController
{

    private PedidoService pedidoService;
    private CreacionService creacionService;
    private TamanioRepository tamanioRepo;
    private DomicilioService domicilioService;
    private MediodepagoService medioPagoService;

    @GetMapping
    public String listarPedidos(Model model, 
            @ModelAttribute("msgerror") String msgerror,
            @ModelAttribute("msgsuccess") String msgsuccess,
            @AuthenticationPrincipal Usuario usuario)
    {
        List<Pedido> pedidos = pedidoService.getPedidosDelUsuario(usuario);
        model.addAttribute("pedidos", pedidos);
        
        return "pedidos";
    }
    
    @PostMapping("/del/{id}")
    public String deletePedido(@PathVariable(name = "id") Pedido pedido, RedirectAttributes redirectAttributes)
    {
        if(pedido==null)
        {
            redirectAttributes.addFlashAttribute("msgerror", "ERROR! No se ha podido borrar el pedido");
        }
        else
        {
            pedidoService.borrar(pedido);
            redirectAttributes.addFlashAttribute("msgsuccess", "El pedido de ha borrado correctamente");
        }
        return "redirect:/pedidos";
    }
    
    @GetMapping("/nuevo")
    public String nuevoPedido(Model model,
            @ModelAttribute("pedidonuevo") Pedido pedidoNuevo,
            @ModelAttribute("errors") String erros,
            @AuthenticationPrincipal Usuario usuario)
    {
        pedidoNuevo.updateAmounts();
        model.addAttribute("pedidonuevo", pedidoNuevo);
        model.addAttribute("tamanios", tamanioRepo.findAll());
        model.addAttribute("creaciones", creacionService.getCreacionesByUsuario(usuario));
        model.addAttribute("item", Item.builder().build());
        return "pedido-nuevo";
    }

    @PostMapping("/additem")
    public String addItem(Model model,
            @ModelAttribute Item item,
            @ModelAttribute("pedidonuevo") Pedido pedidoNuevo,
            RedirectAttributes redirectAttrtibuttes)
    {
        try
        {
            pedidoNuevo = pedidoService.addItemToPedido(pedidoNuevo, item);
        }
        catch (EntidadNoExiste ex)
        {
            Logger.getLogger(PedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/pedidos/nuevo";
    }

    @GetMapping("/domicilio")
    public String domiciliosPedido(Model model,
            @ModelAttribute("pedidonuevo") Pedido pedidoNuevo,
            @AuthenticationPrincipal Usuario usuario,
            @ModelAttribute("msgerror") String msgerror)
    {
        msgerror = !StringUtil.HAS_CONTENT.test(msgerror) ? null : msgerror;
        model.addAttribute("msgerror", msgerror);
        pedidoNuevo.updateAmounts();
        pedidoNuevo.setDomicilio(new Domicilio());
        model.addAttribute("pedidonuevo", pedidoNuevo);
        model.addAttribute("domicilios", domicilioService.getDomiciliosByUsuario(usuario));
        return "pedido-nuevo-domicilio";
    }

    @PostMapping("/domicilioadd")
    public String addDomicilioPedido(Model model,
            @ModelAttribute("pedidonuevo") Pedido pedidoNuevo,
            @AuthenticationPrincipal Usuario usuario,
            RedirectAttributes redirectAttributes)
    {
        pedidoNuevo.updateAmounts();
        EstadoPedido estado = EstadoPedido.PENDIENTE_PAGO;
        String returnPage = "redirect:/pedidos/mediopago";
        if (pedidoNuevo.getDomicilio() == null || pedidoNuevo.getDomicilio().getId() == null)
        {
            estado = EstadoPedido.PENDIENTE_ENVIO;
            returnPage = "redirect:/pedidos/domicilio";
            redirectAttributes.addFlashAttribute("msgerror", "Debe seleccionar un domicilio");
        }
        else
        {
            pedidoNuevo.setEstado(estado);
            pedidoNuevo = pedidoService.guardar(pedidoNuevo);
        }
        model.addAttribute("pedidonuevo", pedidoNuevo);
        return returnPage;
    }

    @GetMapping("/mediopago")
    public String medioPagoPedido(Model model,
            @ModelAttribute("pedidonuevo") Pedido pedidoNuevo,
            @AuthenticationPrincipal Usuario usuario,
            @ModelAttribute("msgerror") String msgerror)
    {
        msgerror = !StringUtil.HAS_CONTENT.test(msgerror) ? null : msgerror;
        model.addAttribute("msgerror", msgerror);
        pedidoNuevo.updateAmounts();
        model.addAttribute("pedidonuevo", pedidoNuevo);
        model.addAttribute("mediospago", medioPagoService.getAllMediodepagos(usuario));
        return "pedido-nuevo-mediopago";
    }

    @PostMapping("/mediopagoadd")
    public String addMedioPagoPedido(Model model,
            @ModelAttribute("pedidonuevo") Pedido pedidoNuevo,
            @AuthenticationPrincipal Usuario usuario,
            RedirectAttributes redirectAttributes,
            SessionStatus sessionStatus)
    {
        pedidoNuevo.updateAmounts();
        String returnPage = "redirect:/pedidos";
        if (pedidoNuevo.getMedioPago() == null || pedidoNuevo.getMedioPago().getId() == null)
        {
            returnPage = "redirect:/pedidos/mediopago";
            redirectAttributes.addFlashAttribute("msgerror", "Debe seleccionar un medio de pago");
        }
        else
        {
            pedidoNuevo.setEstado(EstadoPedido.ENVIADO);
            pedidoNuevo = pedidoService.guardar(pedidoNuevo);
        }
        model.addAttribute("pedidonuevo", pedidoNuevo);
        sessionStatus.setComplete();
        return returnPage;
    }

    @ModelAttribute("pedidonuevo")
    public Pedido pedidoNuevo(Authentication auth)
    {
        Pedido pedidoNuevo = Pedido.builder()
                .created(new Date())
                .lastUpdate(new Date())
                .estado(EstadoPedido.NUEVO)
                .user((Usuario) auth.getPrincipal())
                .build();
        return pedidoNuevo;
    }
}
