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
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Domicilio;
import uy.edu.ort.obligatorio.pizzahurt.repository.DomicilioRepository;

@Service
public class DomicilioService
{
    private final DomicilioRepository domicilioRepo;
    
    @Autowired
    public DomicilioService(DomicilioRepository domicilioRepo) {
        this.domicilioRepo = domicilioRepo;
    }

    public List<Domicilio> getAllDomicilios() {
        return domicilioRepo.findAll();
    }

    public Optional<Domicilio> getDomicilioById(Long id) {
        return domicilioRepo.findById(id);
    }

    public Domicilio createDomicilio(@Valid Domicilio domicilio) {
        return domicilioRepo.save(domicilio);
    }

    public Domicilio updateDomicilio(Long id,@Valid Domicilio domicilio) {
        domicilio.setId(id);
        return domicilioRepo.save(domicilio);
    }

    public void deleteDomicilio(Long id) {
        domicilioRepo.deleteById(id);
    }
}



