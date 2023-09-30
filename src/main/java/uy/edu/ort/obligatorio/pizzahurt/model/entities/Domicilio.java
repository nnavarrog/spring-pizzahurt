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
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Domicilio {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "{campo.notNull}")
    @NotEmpty(message = "{campo.notEmpty}")
    private String barrio;

    @NotNull(message = "{campo.notNull}")
    @NotEmpty(message = "{campo.notEmpty}")
    private String codigo_postal;

    @NotNull(message = "{campo.notNull}")
    @NotEmpty(message = "{campo.notEmpty}")
    private String calle;

    @NotNull(message = "{campo.notNull}")
    @Min(value = 0, message = "{numero.positivo}")
    private int ndepuerta;

    @NotNull(message = "{campo.notNull}")
    @Min(value = 0, message = "{numero.positivo}")
    private int apto;

    private String observaciones;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @Override
    public String toString() {
        return "Domicilios{" + "id=" + id + ", barrio=" + barrio + ", codigo_postal=" + codigo_postal + ", calle=" + calle + ", ndepuerta=" + ndepuerta + ", apto=" + apto + ", observaciones=" + observaciones + '}';
    }
}
