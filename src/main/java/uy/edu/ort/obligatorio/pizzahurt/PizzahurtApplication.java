package uy.edu.ort.obligatorio.pizzahurt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class PizzahurtApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzahurtApplication.class, args);
	}

}
