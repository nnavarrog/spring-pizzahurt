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
import uy.edu.ort.obligatorio.pizzahurt.exceptions.EntidadNoExiste;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Domicilio;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Usuario;
import uy.edu.ort.obligatorio.pizzahurt.repository.DomicilioRepository;
import uy.edu.ort.obligatorio.pizzahurt.repository.UsuarioRepository;

@Service
@AllArgsConstructor
public class DomicilioService
{
    private  DomicilioRepository domicilioRepo;


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

    @Transactional
    public void agregarDomicilio(Domicilio domicilio)  {
        domicilioRepo.save(domicilio);
    }

    public List<Domicilio> getDomiciliosByUsuario(Usuario usuario){
        return domicilioRepo.findByUsuario(usuario);
    }
}



