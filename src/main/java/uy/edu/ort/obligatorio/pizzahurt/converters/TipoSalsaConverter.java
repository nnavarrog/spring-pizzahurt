package uy.edu.ort.obligatorio.pizzahurt.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.TipoSalsa;
import uy.edu.ort.obligatorio.pizzahurt.repository.TipoSalsaRepository;


@Component
public class TipoSalsaConverter implements Converter<Long, TipoSalsa> {

    private TipoSalsaRepository tipoSalsaRepository;

    @Override
    public TipoSalsa convert(Long id){
        return tipoSalsaRepository.getById(id);
    }
}
