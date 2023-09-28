/*
 * NO LICENCE 
 * Author: Ing. Nicolás Navarro Gutérrez
 */
package uy.edu.ort.obligatorio.pizzahurt.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uy.edu.ort.obligatorio.pizzahurt.exceptions.EntidadNoExiste;
import uy.edu.ort.obligatorio.pizzahurt.model.dto.NewItemDto;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.EstadoPedido;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Item;
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
    private UsuarioService userService;
    private ItemService itemService;

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
        Usuario user = userService
                .getUsuarioById(userId)
                .orElseThrow(() -> new EntidadNoExiste("El Usuario de esta sesión NO existe."));
        return nuevo(user);
    }

    public Pedido guardar(@NotNull @Valid Pedido pedido)
    {
        return pedidoRepo.save(pedido);
    }

    public Pedido addItemToPedido(@NotNull Pedido pedido, @NotNull Item item) throws EntidadNoExiste
    {
        pedido.getItems().add(item);
        pedido = pedidoRepo.save(pedido);
        item.setPedido(pedido);
        item = itemService.addItem(item);
        return pedido;
    }
}
