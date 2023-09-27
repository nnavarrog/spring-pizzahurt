package uy.edu.ort.obligatorio.pizzahurt.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.TipoMasa;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Topins;
import uy.edu.ort.obligatorio.pizzahurt.repository.TipoMasaRopository;
import uy.edu.ort.obligatorio.pizzahurt.repository.TopinRepository;


@Component
public class TopinsConverter implements Converter<Long, Topins> {

    private TopinRepository topinRepository;

    @Override
    public Topins convert(Long id){
        return topinRepository.getById(id);
    }
}
