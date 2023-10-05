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

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    private Long id;

    @NotBlank(groups = MedioPago.class, message = "Debe ingresar al menos un emisor")
    @Column(length = 15)
    private String emisor_tarjeta;

    @NotNull (message = "Debe ingresar una fecha de vencimiento")
    private Date fecha_de_vencimiento;

    @NotBlank(groups = MedioPago.class, message = "Debe ingresar un número de tarjeta")
    @Size(groups = MedioPago.class,min = 13, max = 18)
    private String numero_de_tarjeta;

    @NotBlank(groups = MedioPago.class, message = "Debe ingresar el código cvv")
    @Size(min = 3, max = 3,groups = MedioPago.class)
    @Column(length = 3)
    private String cvv;

    @ManyToOne
    @JoinColumn(name= "usuario_id")
    private Usuario usuario;

    @Transient
    @FechaExpiracion(groups = MedioPago.class, message = "La fecha de vencimiento debe ser posterior a la fecha actual")
    @Pattern(regexp = "^(0[1-9]|1[0-2])\\/\\d{2}$",groups = MedioPago.class, message = "Debe ingresar un fecha válida")
    private String fecVtoForm;


    public String getFecVtoForm() {
        if(fecha_de_vencimiento!=null){
            SimpleDateFormat formato = new SimpleDateFormat("MM/yy");

            return formato.format(fecha_de_vencimiento);
        }
        return fecVtoForm;

    }
    
    public String getMediooShortLabel()
    {
        return (new StringBuilder())
                .append(emisor_tarjeta)
                .append("<br>")
                .append(numero_de_tarjeta)
                .append(" ")
                .append(getFecVtoForm())
                .toString();
    }
}
