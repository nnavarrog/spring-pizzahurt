package uy.edu.ort.obligatorio.pizzahurt.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Creacion;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.TipoMasa;
import uy.edu.ort.obligatorio.pizzahurt.repository.CreacionRepository;
import uy.edu.ort.obligatorio.pizzahurt.repository.TipoMasaRopository;


@Component
public class CreacionConverter implements Converter<Long, Creacion> {

    private CreacionRepository creacionRepository;

    @Override
    public Creacion convert(Long id){
        return creacionRepository.getById(id);
    }
}
