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
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.MedioDePago;
import uy.edu.ort.obligatorio.pizzahurt.repository.MediodepagoRepository;

@Service
@AllArgsConstructor
public class MediodepagoService
{
    private final MediodepagoRepository mediodepagorepo;


    public List<MedioDePago> getAllMediodepagos() {
        return mediodepagorepo.findAll();
    }

    @Transactional
    public void createMediodepago(@Valid MedioDePago mediodepago) {
        mediodepagorepo.save(mediodepago);
    }

    @Transactional
    public void deleteMediodepago(MedioDePago mediodepago) {
        mediodepagorepo.delete(mediodepago);
    }
}



