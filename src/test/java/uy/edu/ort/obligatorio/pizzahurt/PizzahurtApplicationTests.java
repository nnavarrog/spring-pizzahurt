package uy.edu.ort.obligatorio.pizzahurt;

import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.TipoMasa;

//@SpringBootTest
class PizzahurtApplicationTests {

	//@Test
	void contextLoads() {
            TipoMasa tipoMasa = TipoMasa.builder()
                    .nombre("Integral")
                    .precio(BigDecimal.valueOf(150))
                    .build();
            
            Assertions.assertNull(tipoMasa.getId());
            
	}

}
