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

package uy.edu.ort.obligatorio.pizzahurt.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Creacion;
import uy.edu.ort.obligatorio.pizzahurt.repository.CreacionRepository;


@Component
public class CreacionConverter implements Converter<Long, Creacion> {

    private CreacionRepository creacionRepository;

    @Override
    public Creacion convert(Long id){
        return creacionRepository.getById(id);
    }
}
