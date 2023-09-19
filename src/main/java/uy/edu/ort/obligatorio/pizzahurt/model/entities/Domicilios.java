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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class Domicilios {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotEmpty
    private String barrio;

    @NotNull
    @NotEmpty
    private String codigo_postal;

    @NotNull
    @NotEmpty
    private String calle;

    @NotNull
    @NotEmpty
    private int ndepuerta;

    private int apto;

    private String observaciones;

    @Override
    public String toString() {
        return "Domicilios{" + "id=" + id + ", barrio=" + barrio + ", codigo_postal=" + codigo_postal + ", calle=" + calle + ", ndepuerta=" + ndepuerta + ", apto=" + apto + ", observaciones=" + observaciones + '}';
    }
}
