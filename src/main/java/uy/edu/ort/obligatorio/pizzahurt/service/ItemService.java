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
package uy.edu.ort.obligatorio.pizzahurt.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.ort.obligatorio.pizzahurt.exceptions.EntidadNoExiste;
import uy.edu.ort.obligatorio.pizzahurt.model.dto.NewItemDto;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Creacion;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Item;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Pedido;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Tamanio;
import uy.edu.ort.obligatorio.pizzahurt.repository.CreacionRepository;
import uy.edu.ort.obligatorio.pizzahurt.repository.ItemRepository;
import uy.edu.ort.obligatorio.pizzahurt.repository.PedidoRepository;
import uy.edu.ort.obligatorio.pizzahurt.repository.TamanioRepository;

@Service
public class ItemService
{

    @Autowired
    private ItemRepository itemRepo;

    @Autowired
    private TamanioRepository tamanioRepo;

    @Autowired
    private PedidoRepository pedidoRepo;

    @Autowired
    private CreacionRepository creacionRepo;

    public Item addNewItem(@Valid @NotNull NewItemDto data) throws EntidadNoExiste
    {
        Item item = newItem(data);
        return itemRepo.save(item);
    }

    public Item newItem(@Valid @NotNull NewItemDto data) throws EntidadNoExiste
    {
        Creacion creacion = (data.getIdCreacion() != null || data.getIdCreacion() > 0)
                ? creacionRepo
                        .findById(data.getIdCreacion())
                        .orElseThrow(() -> new EntidadNoExiste("La entidad CREACION con ID " + data.getIdCreacion() + " NO existe."))
                : null;

        Tamanio tamanio = (data.getIdTamanio() != null || data.getIdTamanio() > 0)
                ? tamanioRepo
                        .findById(data.getIdTamanio())
                        .orElseThrow(() -> new EntidadNoExiste("La entidad TAMANIO con ID " + data.getIdTamanio() + " NO existe."))
                : null;

        Pedido pedido = (data.getIdPedido() != null || data.getIdPedido() > 0)
                ? pedidoRepo
                        .findById(data.getIdPedido())
                        .orElseThrow(() -> new EntidadNoExiste("La entidad PEDIDO con ID " + data.getIdPedido() + " NO existe."))
                : null;

        return Item.builder()
                .nombre(data.getNombre())
                .cantidad(data.getCantidad())
                .creacion(creacion)
                .tamanio(tamanio)
                .pedido(pedido)
                .build();
    }
}
