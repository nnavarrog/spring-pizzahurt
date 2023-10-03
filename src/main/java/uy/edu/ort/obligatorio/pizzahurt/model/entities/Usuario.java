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

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails{

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotEmpty(message = "El email no puede ser vacío.")
    @Email
//	@Column(length = 20)
    private String email;

    @NotNull
    @NotEmpty(message = "El nombre no puede ser vacío.")
    private String nombre;

    @NotNull
    @NotEmpty(message = "El password no puede ser vacío.")
//	@Column(length = 20)
    @Size(min = 10)
    private String password;

    @NotNull
    @NotEmpty(message = "El teléfono no puede ser vacío.")
    private String telefono;

    @NotNull
    //@Past
    @Builder.Default
    private Date createDate = new Date();

    @NotNull
//    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}_\\d{2}:\\d{2}:\\d{2}") YYYY-MM-DD_hh:mm:ss
    @Builder.Default
    private Date lstUpdate = new Date();

    @NotNull
    private boolean activo;
    
    @Builder.Default
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.PERSIST)
    private List<Domicilio> domicilios = new LinkedList<>();
  
    @Builder.Default
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.PERSIST)
    private List<MedioDePago> mediosdepago = new LinkedList<>();
    
    @Builder.Default
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.PERSIST)
    private List<Creacion> creaciones = new LinkedList<>();
   
    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<Pedido> pedidos = new LinkedList<>();

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", email=" + email + ", Nombre=" + nombre + ", password=" + password + ", Telefono=" + telefono + ", createDate=" + createDate + ", lstUpdate=" + lstUpdate + ", activo=" + (activo ? "Si" : "No") + '}';
    }
    
    @Builder.Default
    @Transient
    private List<GrantedAuthority> authorities = new LinkedList<>();

    @Override
    public String getUsername()
    {
        return this.nombre;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return this.activo;
    }
}
