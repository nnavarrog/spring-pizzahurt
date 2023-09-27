package uy.edu.ort.obligatorio.pizzahurt.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.TipoMasa;
import uy.edu.ort.obligatorio.pizzahurt.repository.TipoMasaRopository;


@Component
public class TipoMasaConverter implements Converter<Long, TipoMasa> {

    private TipoMasaRopository tipoMasaRopository;

    @Override
    public TipoMasa convert(Long id){
        return tipoMasaRopository.getById(id);
    }
}
