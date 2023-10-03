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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
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
    public SecurityFilterChain filterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception
    {
        return http
                .authorizeHttpRequests(auth ->
                {
                    auth.requestMatchers(antMatcher("/h2-console/")).permitAll()
                            .requestMatchers(mvc.pattern("/creaciones")).hasAnyRole("USER")
                            .requestMatchers(mvc.pattern("/creaciones/**")).hasRole("USER")
                            .requestMatchers(mvc.pattern("/domicilios")).hasAnyRole("USER")
                            .requestMatchers(mvc.pattern("/domicilios/**")).hasAnyRole("USER")
                            .requestMatchers(mvc.pattern("/medios-de-pago")).hasAnyRole("USER")
                            .requestMatchers(mvc.pattern("/medios-de-pago/**")).hasAnyRole("USER")
                            .requestMatchers(mvc.pattern("/pedidos")).hasAnyRole("USER")
                            .requestMatchers(mvc.pattern("/pedidos/**")).hasAnyRole("USER")
                            .requestMatchers(mvc.pattern(HttpMethod.POST, "/login")).permitAll()
                            .requestMatchers(mvc.pattern("/")).permitAll()
                            .requestMatchers(mvc.pattern("/**")).permitAll()
                            .anyRequest().authenticated();
                })
                .formLogin(login ->
                {
                    login.loginPage("/")
                            .defaultSuccessUrl("/creaciones");
                })
                .logout(logout ->
                {
                    logout.logoutUrl("/logout")
                            .logoutSuccessUrl("/")
                            .permitAll();
                })
                .build();
    }

    @Bean
    public MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector)
    {
        return new MvcRequestMatcher.Builder(introspector);
    }
}
