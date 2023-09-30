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
import uy.edu.ort.obligatorio.pizzahurt.exceptions.EntidadNoExiste;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Domicilio;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Usuario;
import uy.edu.ort.obligatorio.pizzahurt.repository.DomicilioRepository;
import uy.edu.ort.obligatorio.pizzahurt.repository.UsuarioRepository;

@Service
public class DomicilioService
{
    private final DomicilioRepository domicilioRepo;
    private final UsuarioRepository usuarioRepo;
    
    @Autowired
    public DomicilioService(DomicilioRepository domicilioRepo, UsuarioRepository usuarioRepo) {
        this.domicilioRepo = domicilioRepo;
        this.usuarioRepo = usuarioRepo;
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
    
    public void agregarDomicilio(Long id,Domicilio domicilio) throws EntidadNoExiste {
        Usuario usuario = usuarioRepo.findById(id).orElseThrow(() -> new EntidadNoExiste("No existe el usuario"));
//        usuario.getDomicilios().add(domicilio);
        domicilio.setUsuario(usuario);
        domicilioRepo.save(domicilio);
    }
}



