package uy.edu.ort.obligatorio.pizzahurt.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Creacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tipo_masa_id")
    private TipoMasa tipoMasa;

    @ManyToOne
    @JoinColumn(name = "tipo_salsa_id")
    private TipoSalsa tipoSalsa;

    @ManyToOne
    @JoinColumn(name = "tipo_queso_id")
    private TipoQueso tipoQueso;


    @ManyToMany
    @JoinTable(name = "creacion_topins" , joinColumns = @JoinColumn(name = "creacion_id") , inverseJoinColumns = @JoinColumn(name = "topin_id"))
    private List<Topins> topins;

}
