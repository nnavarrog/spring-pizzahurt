package uy.edu.ort.obligatorio.pizzahurt.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Pedido;
import uy.edu.ort.obligatorio.pizzahurt.repository.PedidoRepository;

@Component
public class PedidoConverter implements Converter<Long, Pedido>
{

    private PedidoRepository tamanioRepo;

    @Override
    public Pedido convert(Long id)
    {
        return tamanioRepo.findById(id).
                orElse(null);
    }
}
