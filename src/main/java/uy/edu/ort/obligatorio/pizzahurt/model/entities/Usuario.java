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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotEmpty
    @Email
//	@Column(length = 20)
    private String email;

    @NotNull
    @NotEmpty
    private String Nombre;

    @NotNull
    @NotEmpty
//	@Column(length = 20)
    private String password;

    @NotNull
    @NotEmpty
    private String Telefono;

    private String passSalt;

    @NotNull
    @NotEmpty
    @Past
    @CreationTimestamp
    private Timestamp createDate;

    @NotNull
    @NotEmpty
//    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}_\\d{2}:\\d{2}:\\d{2}") YYYY-MM-DD_hh:mm:ss
    @UpdateTimestamp
    private Timestamp lstUpdate;

    @NotNull
    private boolean activo;

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", email=" + email + ", Nombre=" + Nombre + ", password=" + password + ", Telefono=" + Telefono + ", createDate=" + createDate + ", lstUpdate=" + lstUpdate + ", activo=" + (activo?"Si":"No") + '}';
    }
    
}