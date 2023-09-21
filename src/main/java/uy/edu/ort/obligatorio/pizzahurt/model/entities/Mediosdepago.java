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

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uy.edu.ort.obligatorio.pizzahurt.constraints.CreditCard;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Mediosdepago {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotEmpty
    private String emisor_tarjeta;

    @NotNull
    @Past
    private Date fecha_de_vencimiento;

    @NotNull
    @Size(min = 13, max = 18)
    @CreditCard
    private String numero_de_tarjeta;

    @NotNull
    @Size(min = 3, max = 3)
    private int cvv;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @Override
    public String toString() {
        return "Mediosdepago{" + "id=" + id + ", emisor_tarjeta=" + emisor_tarjeta + ", fecha_de_vencimiento=" + fecha_de_vencimiento + ", numero_de_tarjeta=" + numero_de_tarjeta + ", cvv=" + cvv + '}';
    }
}
