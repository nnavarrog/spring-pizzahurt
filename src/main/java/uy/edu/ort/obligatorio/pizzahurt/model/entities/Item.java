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

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uy.edu.ort.obligatorio.pizzahurt.utils.AmountUtil;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "creacionId")
    private Creacion creacion;

    @Min(value = 1)
    private int cantidad;

    @ManyToOne
    @JoinColumn(name = "tamanioId")
    private Tamanio tamanio;

    @ManyToOne
    @JoinColumn(name = "pedidoId")
    private Pedido pedido;

    /**
     * Devuelve el precio total del item
     *
     * @return
     */
    public BigDecimal getPrice()
    {
        return tamanio.getPrecio()
                .add(creacion.getPrice())
                .multiply(new BigDecimal(cantidad));
    }

    public String getFormatedPrice()
    {
        return AmountUtil.getFormatedPrice(getPrice());
    }
}
