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
package uy.edu.ort.obligatorio.pizzahurt.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import uy.edu.ort.obligatorio.pizzahurt.utils.AmountUtil;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class PizzaComponent
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 90)
    @Column(length = 90)
    protected String nombre;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 300)
    @Column(length = 300)
    protected String descripcion;

    @NotNull
    @Min(value = 0)
    protected BigDecimal precio;

    public String getFormatedLabel()
    {
        return AmountUtil.getFormatedPrice(precio, nombre);
    }
    
    public String getFormatedPrice()
    {
        return AmountUtil.getFormatedPrice(precio);
    }

}
