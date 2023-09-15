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

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import java.util.Date;

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
    @NotNull
    @Past
    private Date createDate;

    @NotNull
    @NotEmpty
//    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}_\\d{2}:\\d{2}:\\d{2}") YYYY-MM-DD_hh:mm:ss
    private String lstUpdate;

    @NotNull
    private boolean activo;

    public Usuario() {
        super();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getPassword() {
        return password;
    }

    public String getTelefono() {
        return Telefono;
    }

    public String getPassSalt() {
        return passSalt;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getLstUpdate() {
        return lstUpdate;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public void setPassSalt(String passSalt) {
        this.passSalt = passSalt;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setLstUpdate(String lstUpdate) {
        this.lstUpdate = lstUpdate;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", email=" + email + ", Nombre=" + Nombre + ", password=" + password + ", Telefono=" + Telefono + ", createDate=" + createDate + ", lstUpdate=" + lstUpdate + ", activo=" + (activo?"Si":"No") + '}';
    }
    
}
