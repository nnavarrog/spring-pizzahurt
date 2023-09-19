package uy.edu.ort.obligatorio.pizzahurt.model.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class PizzaComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 90)
    @Column(length = 90)
    private String nombre;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 300)
    @Column(length = 300)
    private String descripcion;

    @NotNull
    @Min(value=0)
    private BigDecimal precio;




}
