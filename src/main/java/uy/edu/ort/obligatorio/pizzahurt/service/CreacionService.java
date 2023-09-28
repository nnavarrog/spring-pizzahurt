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

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.*;
import uy.edu.ort.obligatorio.pizzahurt.repository.*;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class CreacionService {

    private  CreacionRepository creacionRepository;

    private TipoMasaRopository tipoMasaRopository;

    private TipoQuesoRepository tipoQuesoRepository;

    private TipoSalsaRepository tipoSalsaRepository;

    private TopinRepository topinRepository;

    public record Listas(List<TipoMasa> tipoMasaList, List<TipoQueso> tipoQuesoList, List<TipoSalsa> tipoSalsaList, List<Topins> topinsList){

    }

    /**
     * Obtiene todos los ingredientes para dar de alta una creación
     * @return Listas de todos los ingredientes
     */
    public Listas obtenerIngredientesCreacion(){
        return new Listas(tipoMasaRopository.findAll(),tipoQuesoRepository.findAll(),tipoSalsaRepository.findAll(),topinRepository.findAll());
    }

    /**
     * Da de alta una creación
     * @param creacion
     */
    //TODO Validaciones
    @Transactional
    public void altaCreacion(Creacion creacion){
        creacionRepository.save(creacion);
    }


    /**
     * Obtiene una lista de creaciones
     * @return
     */
    public List<Creacion> obtenerCreaciones(){
      return creacionRepository.findAll();
    }

    /**
     * Elimina la creación
     * @param creacion
     */
    public void eliminarCreacion(Creacion creacion){
        creacionRepository.delete(creacion);
    }



}
