/*
 * NO LICENCE 
 * Author: Ing. Nicolás Navarro Gutérrez
 */
package uy.edu.ort.obligatorio.pizzahurt.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uy.edu.ort.obligatorio.pizzahurt.exceptions.EntidadNoExiste;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.EstadoPedido;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Pedido;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Usuario;
import uy.edu.ort.obligatorio.pizzahurt.repository.PedidoRepository;

/**
 *
 * @author nnavarro
 */
@Service
@AllArgsConstructor
public class PedidoService
{
    private PedidoRepository pedidoRepo;
    
    public Pedido nuevo(@NotNull @Valid Usuario usuario)
    {
        Pedido nuevo = Pedido.builder()
                .estado(EstadoPedido.NUEVO)
                .user(usuario)
                .build();
        return pedidoRepo.save(nuevo);
    }
    
    public Pedido nuevo(@NotNull Long userId) throws EntidadNoExiste
    {
        Usuario user = null/*TODO: Se esperan los servicios del usuario*/;
        return nuevo(user);
    }
}
