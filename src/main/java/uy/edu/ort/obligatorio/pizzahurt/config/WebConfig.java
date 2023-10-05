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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer
{

    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        //registry.addViewController("/").setViewName("index");
        registry.addViewController("/contacto").setViewName("contacto");
        registry.addViewController("/about").setViewName("about");
        registry.addViewController("/login").setViewName("index");
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver,SpringSecurityDialect sec)
    {
        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.addDialect(sec);
        return templateEngine;
    }
}
