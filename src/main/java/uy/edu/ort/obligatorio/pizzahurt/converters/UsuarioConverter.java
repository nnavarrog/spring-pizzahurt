package uy.edu.ort.obligatorio.pizzahurt.converters;

import java.util.Optional;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Usuario;
import uy.edu.ort.obligatorio.pizzahurt.repository.UsuarioRepository;


@Component
public class UsuarioConverter implements Converter<Long, Usuario> {

    private UsuarioRepository usuarioRepo;

    @Override
    public Usuario convert(Long id){
        return usuarioRepo.findById(id).get();
    }
}
