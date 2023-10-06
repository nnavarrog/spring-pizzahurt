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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uy.edu.ort.obligatorio.pizzahurt.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uy.edu.ort.obligatorio.pizzahurt.constraints.CreditCard;
import uy.edu.ort.obligatorio.pizzahurt.constraints.FechaExpiracion;
import uy.edu.ort.obligatorio.pizzahurt.constraints.MedioPago;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedioDePago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NotBlank(groups = MedioPago.class, message = "Emisor de tarjeta inválido")
    @Size(groups = MedioPago.class,min = 4, max = 20, message = "")
    @Column(length = 20)
    @JsonProperty("emisor_tarjeta")
    private String emisor_tarjeta;

    @NotNull (message = "Debe ingresar una fecha de vencimiento")
    @JsonIgnore
    private Date fecha_de_vencimiento;

    @NotBlank(groups = MedioPago.class, message = "Emisor de tarjeta incorrecto")
    @Size(groups = MedioPago.class,min = 13, max = 20)
    @JsonProperty("numero_de_tarjeta")
    //@CreditCard(groups = MedioPago.class, message = "El número de tarjeta es inválido")
    private String numero_de_tarjeta;

    @NotBlank(groups = MedioPago.class, message = "Debe ingresar el código cvv")
    @Size(min = 3, max = 3,groups = MedioPago.class)
    @Column(length = 3)
    @JsonProperty("cvv")
    private String cvv;

    @ManyToOne
    @JoinColumn(name= "usuario_id")
    @JsonIgnore
    private Usuario usuario;

    @Transient
    @FechaExpiracion(groups = MedioPago.class, message = "La fecha de vencimiento debe ser posterior a la fecha actual")
    @Pattern(regexp = "^(0[1-9]|1[0-2])\\/\\d{2}$",groups = MedioPago.class, message = "Debe ingresar un fecha válida")
    @JsonProperty("fecVtoForm")
    private String fecVtoForm;


    public String getFecVtoForm() {
        if(fecha_de_vencimiento!=null){
            SimpleDateFormat formato = new SimpleDateFormat("MM/yy");

            return formato.format(fecha_de_vencimiento);
        }
        return fecVtoForm;

    }

    @JsonIgnore
    public String getMediooShortLabel()
    {
        return (new StringBuilder())
                .append(emisor_tarjeta)
                .append(" | ")
                .append(numero_de_tarjeta)
                .append(" ")
                .append(getFecVtoForm())
                .toString();
    }
}
