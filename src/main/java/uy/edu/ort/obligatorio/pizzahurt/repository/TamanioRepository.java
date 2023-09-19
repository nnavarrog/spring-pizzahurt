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
package uy.edu.ort.obligatorio.pizzahurt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Tamanio;


public interface TamanioRepository extends JpaRepository<Tamanio, Long>
{
    
}
