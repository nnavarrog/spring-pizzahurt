package uy.edu.ort.obligatorio.pizzahurt.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import uy.edu.ort.obligatorio.pizzahurt.utils.AmountUtil;

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

    @NotBlank
    @Size(min = 1, max = 25)
    @Column(length = 25)
    private String nombre;

    @ManyToOne
    @NotNull(message = "Debe ingresar una masa")
    @JoinColumn(name = "tipo_masa_id")
    private TipoMasa tipoMasa;

    @ManyToOne
    @NotNull(message = "Debe ingresar una salsa")
    @JoinColumn(name = "tipo_salsa_id")
    private TipoSalsa tipoSalsa;

    @ManyToOne
    @NotNull(message = "Debe ingresar un queso")
    @JoinColumn(name = "tipo_queso_id")
    private TipoQueso tipoQueso;


    @ManyToMany(fetch = FetchType.EAGER)
    @NotEmpty(message = "Debe ingresar al menos un topping")
    @JoinTable(name = "creacion_topins" , joinColumns = @JoinColumn(name = "creacion_id") , inverseJoinColumns = @JoinColumn(name = "topin_id"))
    private List<Topins> topins;

    @ManyToOne
    @JoinColumn(name= "usuario_id")
    private Usuario usuario;


    /**
     * Get creation total price
     * @return BigDecimal total
     */
    public BigDecimal getPrice(){

        BigDecimal total = BigDecimal.ZERO;

        if(tipoMasa!=null) {
            total = total.add(tipoMasa.getPrecio());
        }

        if (tipoQueso!=null)
            total = total.add(tipoQueso.getPrecio());

        if(tipoSalsa!=null)
            total = total.add(tipoSalsa.getPrecio());

        if(topins != null && !topins.isEmpty()){
            for( Topins topin : topins)
                total = total.add(topin.getPrecio());
        }
        return total;
    }
    
    public String getFormatedLabel()
    {
        return AmountUtil.getFormatedPrice(this.getPrice());
    }
    
    public String getFormatedLabel2()
    {
        return AmountUtil.getFormatedPrice(this.getPrice(), this.nombre);
    }

}
