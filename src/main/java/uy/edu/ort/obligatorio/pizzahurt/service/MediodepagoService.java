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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Mediodepago;
import uy.edu.ort.obligatorio.pizzahurt.repository.MediodepagoRepository;

@Service
public class MediodepagoService
{
    private final MediodepagoRepository mediodepagorepo;
    
    @Autowired
    public MediodepagoService(MediodepagoRepository mediodepagorepo) {
        this.mediodepagorepo = mediodepagorepo;
    }

    public List<Mediodepago> getAllMediodepagos() {
        return mediodepagorepo.findAll();
    }

    public Optional<Mediodepago> getMediodepagoById(Long id) {
        return mediodepagorepo.findById(id);
    }

    public Mediodepago createMediodepago(@Valid Mediodepago mediodepago) {
        return mediodepagorepo.save(mediodepago);
    }

    public Mediodepago updateMediodepago(Long id,@Valid Mediodepago mediodepago) {
        mediodepago.setId(id);
        return mediodepagorepo.save(mediodepago);
    }

    public void deleteMediodepago(Long id) {
        mediodepagorepo.deleteById(id);
    }
}



