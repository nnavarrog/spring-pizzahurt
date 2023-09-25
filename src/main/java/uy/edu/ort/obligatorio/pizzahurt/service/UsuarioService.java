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
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.ort.obligatorio.pizzahurt.exceptions.EntidadNoExiste;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Domicilio;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Mediodepago;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Usuario;
import uy.edu.ort.obligatorio.pizzahurt.repository.UsuarioRepository;

@Service
public class UsuarioService {

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
    
    public Usuario crearUsuario(Usuario usuario) {
        usuario.setActivo(true);
        usuario.setCreateDate(new Date());
        usuario.setLstUpdate(new Date());
       
        return usuarioRepo.save(usuario);
    }
    
    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        usuario.setId(id);
        return usuarioRepo.save(usuario);
    }
    
    public void borrarUsuario(Long id) {
        usuarioRepo.deleteById(id);
    }
    
    public void agregarDomicilio(Long id,@Valid Domicilio domicilio) throws EntidadNoExiste {
        Usuario usuario = this.getUsuarioById(id).orElseThrow(() -> new EntidadNoExiste("No existe el usuario"));
        usuario.getDomicilios().add(domicilio);
        usuarioRepo.save(usuario);
    }
    
    public void agregarMediodepago(Long id,@Valid Mediodepago mediodepago) throws EntidadNoExiste {
        Usuario usuario = this.getUsuarioById(id).orElseThrow(() -> new EntidadNoExiste("No existe el usuario"));
        usuario.getMediosdepago().add(mediodepago);
        usuarioRepo.save(usuario);
    }
    

}
