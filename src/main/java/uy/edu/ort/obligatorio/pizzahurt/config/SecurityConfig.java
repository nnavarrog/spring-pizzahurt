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
package uy.edu.ort.obligatorio.pizzahurt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import uy.edu.ort.obligatorio.pizzahurt.service.UsuarioService;

@Configuration
public class SecurityConfig
{

    @Autowired
    private UsuarioService userService;

    private final String[] sessionedUris =
    {
        "/creaciones", "/creaciones/**", "/domicilios", "/domicilios/**", "/medios-de-pago", "/medios-de-pago/**", "/pedidos", "/pedidos/**"
    };

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        return http
                .authorizeHttpRequests(auth ->
                {
                    auth.requestMatchers("/creaciones", "/creaciones/**", "/domicilios", "/domicilios/**", "/medios-de-pago", "/medios-de-pago/**", "/pedidos", "/pedidos/**").hasAnyRole("USER")
                            .requestMatchers("/", "/**").permitAll();
                            
                }).formLogin(login -> login
                        .loginPage("/")
                        .defaultSuccessUrl("/creaciones"))
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll())
                .build();
    }
}
