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


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.MedioDePago;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Usuario;
import uy.edu.ort.obligatorio.pizzahurt.repository.MediodepagoRepository;

@Service
@AllArgsConstructor
public class MediodepagoService
{
    private final MediodepagoRepository mediodepagorepo;


    public List<MedioDePago> getAllMediodepagos(Usuario usuario) {
        return mediodepagorepo.findByUsuario(usuario);
    }

    @Transactional
    public void createMediodepago(MedioDePago mediodepago) throws ParseException {

        SimpleDateFormat formato = new SimpleDateFormat("MM/yy");
        mediodepago.setFecha_de_vencimiento(formato.parse(mediodepago.getFecVtoForm()));
        mediodepagorepo.save(mediodepago);
    }

    @Transactional
    public void deleteMediodepago(MedioDePago mediodepago) {
        mediodepagorepo.delete(mediodepago);
    }
}



