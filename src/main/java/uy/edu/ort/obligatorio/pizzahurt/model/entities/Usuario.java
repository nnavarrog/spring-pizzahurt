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

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
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
    @Size(min = 10,message = "El password debe tener 10 caracteres como mínimo")
    private String password;

    @NotNull
    @NotEmpty(message = "El teléfono no puede ser vacío.")
    private String telefono;

    @NotNull
    //@Past
    @Builder.Default
    @JsonIgnore
    private Date createDate = new Date();

    @NotNull
//    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}_\\d{2}:\\d{2}:\\d{2}") YYYY-MM-DD_hh:mm:ss
    @Builder.Default
    @JsonIgnore
    private Date lstUpdate = new Date();

    @NotNull
    @JsonIgnore
    private boolean activo;
    
    @Builder.Default
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Domicilio> domicilios = new LinkedList<>();
  
    @Builder.Default
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<MedioDePago> mediosdepago = new LinkedList<>();
    
    @Builder.Default
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Creacion> creaciones = new LinkedList<>();
   
    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Pedido> pedidos = new LinkedList<>();

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", email=" + email + ", Nombre=" + nombre + ", password=" + password + ", Telefono=" + telefono + ", createDate=" + createDate + ", lstUpdate=" + lstUpdate + ", activo=" + (activo ? "Si" : "No") + '}';
    }
    
    @Builder.Default
    @Transient
    @JsonIgnore
    private List<GrantedAuthority> authorities = new LinkedList<>();

    @Override
    public String getUsername()
    {
        return this.nombre;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled()
    {
        return this.activo;
    }
}
