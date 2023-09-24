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
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Usuario;
import uy.edu.ort.obligatorio.pizzahurt.repository.UsuarioRepository;

@Service
public class UsuarioService
{
    private final UsuarioRepository usuarioRepo;
    
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepo.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepo.findById(id);
    }

    public Usuario createUsuario(@Valid Usuario usuario) {
        return usuarioRepo.save(usuario);
    }

    public Usuario updateUsuario(Long id,@Valid Usuario usuario) {
        usuario.setId(id);
        return usuarioRepo.save(usuario);
    }

    public void deleteUsuario(Long id) {
        usuarioRepo.deleteById(id);
    }
}



