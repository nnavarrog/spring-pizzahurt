package uy.edu.ort.obligatorio.pizzahurt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.TipoSalsa;

public interface TipoSalsaRepository extends JpaRepository<TipoSalsa,Long>  {
}
