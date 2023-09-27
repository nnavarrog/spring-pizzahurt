package uy.edu.ort.obligatorio.pizzahurt.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.TipoMasa;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.TipoQueso;
import uy.edu.ort.obligatorio.pizzahurt.repository.TipoMasaRopository;
import uy.edu.ort.obligatorio.pizzahurt.repository.TipoQuesoRepository;


@Component
public class TipoQuesoConverter implements Converter<Long, TipoQueso> {

    private TipoQuesoRepository tipoQuesoRepository;

    @Override
    public TipoQueso convert(Long id){
        return tipoQuesoRepository.getById(id);
    }
}
