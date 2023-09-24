package uy.edu.ort.obligatorio.pizzahurt;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.*;
import uy.edu.ort.obligatorio.pizzahurt.repository.*;

import java.math.BigDecimal;

@Configuration
public class InitDB {

    @Bean
    CommandLineRunner initDatabase( TamanioRepository tamanioRepository, TipoMasaRopository tipoMasaRopository, TipoQuesoRepository tipoQuesoRepository, TipoSalsaRepository tipoSalsaRepository, TopinRepository topinRepository) {

        return args -> {
            Tamanio tamanioPequeno = Tamanio.builder()
                    .nombre("Pequeño")
                    .descripcion("Tamaño pequeño")
                    .precio(new BigDecimal("10"))
                    .build();

            tamanioRepository.save(tamanioPequeno);

            Tamanio tamanioMediano = Tamanio.builder()
                    .nombre("Mediano")
                    .descripcion("Tamaño mediano")
                    .precio(new BigDecimal("20"))
                    .build();

            tamanioRepository.save(tamanioMediano);

            Tamanio tamanioGrande = Tamanio.builder()
                    .nombre("Grande")
                    .descripcion("Tamaño grande")
                    .precio(new BigDecimal("30"))
                    .build();

            tamanioRepository.save(tamanioGrande);

            TipoMasa tipoMasaGruesa = TipoMasa.builder()
                    .nombre("Gruesa")
                    .descripcion("Una masa gruesa y esponjosa.")
                    .precio(new BigDecimal("5.99"))
                    .build();

            tipoMasaRopository.save(tipoMasaGruesa);

            TipoMasa tipoMasaIntegral = TipoMasa.builder()
                    .nombre("Integral")
                    .descripcion("Una masa integral saludable.")
                    .precio(new BigDecimal("6.99"))
                    .build();

            tipoMasaRopository.save(tipoMasaIntegral);

            TipoMasa tipoMasaSinGluten = TipoMasa.builder()
                    .nombre("Sin Gluten")
                    .descripcion("Una masa sin gluten para personas con intolerancia.")
                    .precio(new BigDecimal("7.99"))
                    .build();

            tipoMasaRopository.save(tipoMasaSinGluten);

            TipoQueso quesoMozzarella = TipoQueso.builder()
                    .nombre("Mozzarella")
                    .descripcion("Queso Mozzarella fresco y suave.")
                    .precio(new BigDecimal("2.99"))
                    .build();

            tipoQuesoRepository.save(quesoMozzarella);

            TipoQueso quesoCheddar = TipoQueso.builder()
                    .nombre("Cheddar")
                    .descripcion("Queso Cheddar madurado con un sabor fuerte.")
                    .precio(new BigDecimal("3.99"))
                    .build();

            tipoQuesoRepository.save(quesoCheddar);

            TipoQueso quesoParmesano = TipoQueso.builder()
                    .nombre("Parmesano")
                    .descripcion("Queso Parmesano envejecido y rallado.")
                    .precio(new BigDecimal("4.99"))
                    .build();

            tipoQuesoRepository.save(quesoParmesano);

            TipoSalsa salsaBBQ = TipoSalsa.builder()
                    .nombre("BBQ")
                    .descripcion("Salsa BBQ ahumada con un toque de dulzura y especias.")
                    .precio(new BigDecimal("3.49"))
                    .build();

            tipoSalsaRepository.save(salsaBBQ);

            TipoSalsa salsaNapolitana = TipoSalsa.builder()
                    .nombre("Napolitana")
                    .descripcion("Salsa napolitana auténtica con tomates San Marzano y albahaca fresca.")
                    .precio(new BigDecimal("2.99"))
                    .build();

            tipoSalsaRepository.save(salsaNapolitana);

            TipoSalsa salsaPomodoro = TipoSalsa.builder()
                    .nombre("Pomodoro")
                    .descripcion("Salsa pomodoro de tomates italianos con un toque de ajo y aceite de oliva.")
                    .precio(new BigDecimal("3.49"))
                    .build();

            tipoSalsaRepository.save(salsaPomodoro);

            Topins toppingPepperoni = Topins.builder()
                    .nombre("Pepperoni")
                    .descripcion("Rodajas de pepperoni picante y sabroso.")
                    .precio(new BigDecimal("1.99"))
                    .build();

            topinRepository.save(toppingPepperoni);

            Topins toppingChampiñones = Topins.builder()
                    .nombre("Champiñones")
                    .descripcion("Champiñones frescos y rebanados.")
                    .precio(new BigDecimal("1.49"))
                    .build();

            topinRepository.save(toppingChampiñones);

            Topins toppingAceitunas = Topins.builder()
                    .nombre("Aceitunas")
                    .descripcion("Aceitunas negras y verdes en rodajas.")
                    .precio(new BigDecimal("1.29"))
                    .build();

            topinRepository.save(toppingAceitunas);



        };

    }

}
