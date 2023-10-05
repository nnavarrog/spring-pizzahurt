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

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Pedido;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Usuario;

public interface PedidoRepository extends JpaRepository<Pedido, Long>
{
    public List<Pedido> findByUser(Usuario usuario);
}
