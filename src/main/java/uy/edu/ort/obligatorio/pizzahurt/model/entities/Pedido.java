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

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uy.edu.ort.obligatorio.pizzahurt.utils.AmountUtil;

/**
 *
 * @author nnavarro
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pedido
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private EstadoPedido estado;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "userId")
    private Usuario user;

    @ManyToOne
    @JoinColumn(name = "medioPagoId")
    private Mediodepago medioPago;

    @ManyToOne
    @JoinColumn(name = "domicilioId")
    private Domicilio domicilio;

    @Builder.Default
    private BigDecimal totalPagarMonto = BigDecimal.ZERO;

    @NotNull
    @NotEmpty
    @Column(columnDefinition = "varchar(3) default 'UYU'")
    @Builder.Default
    private String totalPagarMoneda = "UYU";

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @Builder.Default
    @OneToMany
    @JoinColumn(name = "pedidoId")
    private List<Item> items = new LinkedList<>();


    /**
     * Actualiza los valores del monto del pedido en función de los items.
     */
    public void updateAmounts()
    {
        totalPagarMonto = BigDecimal.ZERO;
        items.forEach(it ->
        {
            totalPagarMonto = totalPagarMonto.add(it.getPrice());
        });
    }   
    
    public BigDecimal getTotalPagarMonto()
    {
        updateAmounts();
        return this.totalPagarMonto;
    }
    
    public String getFormatedPrice()
    {
        return AmountUtil.getFormatedPrice(getTotalPagarMonto());
    }
}
