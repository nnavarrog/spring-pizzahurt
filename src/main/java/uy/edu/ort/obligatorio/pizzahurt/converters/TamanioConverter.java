package uy.edu.ort.obligatorio.pizzahurt.converters;

import java.math.BigDecimal;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Tamanio;
import uy.edu.ort.obligatorio.pizzahurt.repository.TamanioRepository;


@Component
public class TamanioConverter implements Converter<Long, Tamanio> {

    private TamanioRepository tamanioRepo;

    @Override
    public Tamanio convert(Long id){
        return tamanioRepo.findById(id).
                orElse(Tamanio.builder()
                        .nombre("N/A")
                        .descripcion("N/A")
                        .precio(BigDecimal.ZERO)
                        .build());
    }
}
