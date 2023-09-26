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
                    .precio(new BigDecimal("60"))
                    .build();

            tamanioRepository.save(tamanioPequeno);

            Tamanio tamanioMediano = Tamanio.builder()
                    .nombre("Mediano")
                    .descripcion("Tamaño mediano")
                    .precio(new BigDecimal("90"))
                    .build();

            tamanioRepository.save(tamanioMediano);

            Tamanio tamanioGrande = Tamanio.builder()
                    .nombre("Grande")
                    .descripcion("Tamaño grande")
                    .precio(new BigDecimal("120"))
                    .build();

            tamanioRepository.save(tamanioGrande);

            TipoMasa tipoMasaGruesa = TipoMasa.builder()
                    .nombre("Gruesa")
                    .descripcion("Una masa gruesa y esponjosa.")
                    .precio(new BigDecimal("15.99"))
                    .build();

            tipoMasaRopository.save(tipoMasaGruesa);

            TipoMasa tipoMasaIntegral = TipoMasa.builder()
                    .nombre("Integral")
                    .descripcion("Una masa integral saludable.")
                    .precio(new BigDecimal("16.99"))
                    .build();

            tipoMasaRopository.save(tipoMasaIntegral);

            TipoMasa tipoMasaSinGluten = TipoMasa.builder()
                    .nombre("Sin Gluten")
                    .descripcion("Una masa sin gluten para personas con intolerancia.")
                    .precio(new BigDecimal("17.99"))
                    .build();

            tipoMasaRopository.save(tipoMasaSinGluten);

            TipoQueso quesoMozzarella = TipoQueso.builder()
                    .nombre("Mozzarella")
                    .descripcion("Queso Mozzarella fresco y suave.")
                    .precio(new BigDecimal("42.59"))
                    .build();

            tipoQuesoRepository.save(quesoMozzarella);

            TipoQueso quesoCheddar = TipoQueso.builder()
                    .nombre("Cheddar")
                    .descripcion("Queso Cheddar madurado con un sabor fuerte.")
                    .precio(new BigDecimal("43.99"))
                    .build();

            tipoQuesoRepository.save(quesoCheddar);

            TipoQueso quesoParmesano = TipoQueso.builder()
                    .nombre("Parmesano")
                    .descripcion("Queso Parmesano envejecido y rallado.")
                    .precio(new BigDecimal("54.89"))
                    .build();

            tipoQuesoRepository.save(quesoParmesano);
            
            TipoQueso otroQueso = TipoQueso.builder()
                    .nombre("Roquefort")
                    .descripcion("Queso Roquefort envejecido en trozos pequeños.")
                    .precio(new BigDecimal("72.49"))
                    .build();

            tipoQuesoRepository.save(otroQueso);
            
            otroQueso = TipoQueso.builder()
                    .nombre("Cremosos")
                    .descripcion("Queso Philadelphia y Requesón.")
                    .precio(new BigDecimal("63.99"))
                    .build();

            tipoQuesoRepository.save(otroQueso);

            TipoSalsa salsaBBQ = TipoSalsa.builder()
                    .nombre("BBQ")
                    .descripcion("Salsa BBQ ahumada con un toque de dulzura y especias.")
                    .precio(new BigDecimal("13.49"))
                    .build();

            tipoSalsaRepository.save(salsaBBQ);

            TipoSalsa salsaNapolitana = TipoSalsa.builder()
                    .nombre("Napolitana")
                    .descripcion("Salsa napolitana auténtica con tomates San Marzano y albahaca fresca.")
                    .precio(new BigDecimal("12.99"))
                    .build();

            tipoSalsaRepository.save(salsaNapolitana);

            TipoSalsa salsaPomodoro = TipoSalsa.builder()
                    .nombre("Pomodoro")
                    .descripcion("Salsa pomodoro de tomates italianos con un toque de ajo y aceite de oliva.")
                    .precio(new BigDecimal("12.49"))
                    .build();

            tipoSalsaRepository.save(salsaPomodoro);

            Topins toppingPepperoni = Topins.builder()
                    .nombre("Pepperoni")
                    .descripcion("Rodajas de pepperoni picante y sabroso.")
                    .precio(new BigDecimal("31.99"))
                    .build();

            topinRepository.save(toppingPepperoni);

            Topins toppingChampiñones = Topins.builder()
                    .nombre("Champiñones")
                    .descripcion("Champiñones frescos y rebanados.")
                    .precio(new BigDecimal("41.49"))
                    .build();

            topinRepository.save(toppingChampiñones);

            Topins toppingAceitunas = Topins.builder()
                    .nombre("Aceitunas")
                    .descripcion("Aceitunas negras y verdes en rodajas.")
                    .precio(new BigDecimal("18.29"))
                    .build();

            topinRepository.save(toppingAceitunas);
            
            Topins otroToping = Topins.builder()
                    .nombre("Anchoas")
                    .descripcion("Anchoas frescas de la pesca del día.")
                    .precio(new BigDecimal("32.59"))
                    .build();
            topinRepository.save(otroToping);
            
            otroToping = Topins.builder()
                    .nombre("Ananá")
                    .descripcion("Piña en almibar en rodajas.")
                    .precio(new BigDecimal("22.59"))
                    .build();
            topinRepository.save(otroToping);

            otroToping = Topins.builder()
                    .nombre("Huevo")
                    .descripcion("Huevo duro en finas láminas.")
                    .precio(new BigDecimal("12.99"))
                    .build();
            topinRepository.save(otroToping);
            
            otroToping = Topins.builder()
                    .nombre("Tocino")
                    .descripcion("Panceta tostada y fritada en manteca de cerdo.")
                    .precio(new BigDecimal("21.29"))
                    .build();
            topinRepository.save(otroToping);

            otroToping = Topins.builder()
                    .nombre("Huevo Frito")
                    .descripcion("Cubierta de huevos de gallina de campo fritos en aceite de oliva.")
                    .precio(new BigDecimal("22.59"))
                    .build();
            topinRepository.save(otroToping);
        };

    }

}
