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
package uy.edu.ort.obligatorio.pizzahurt.controllers;

import com.sun.security.auth.UserPrincipal;
import jakarta.validation.Valid;
import java.text.ParseException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.MedioDePago;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Usuario;
import uy.edu.ort.obligatorio.pizzahurt.service.MediodepagoService;
import uy.edu.ort.obligatorio.pizzahurt.service.UsuarioService;

@RestController
@RequestMapping("/api")
public class WsrestController {

    private UsuarioService usuarioservice;
    private MediodepagoService mediodepagoService;

    public WsrestController(UsuarioService usuarioservice, MediodepagoService mediodepagoService) {
        this.usuarioservice = usuarioservice;
        this.mediodepagoService = mediodepagoService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@AuthenticationPrincipal UserPrincipal userPrincipal) {

        return ResponseEntity.ok("Usuario autenticado: " + userPrincipal.getName());
    }

    @PostMapping("/registrarse")
    public ResponseEntity registrarse(@Valid @RequestBody Usuario usuario) {
        usuarioservice.crearUsuario(usuario);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/mediosdepago/{id}")
    public ResponseEntity<List<MedioDePago>> mediosdepago(@PathVariable("id") Usuario usuario) {
        try {
            List<MedioDePago> mediosdepago = mediodepagoService.getAllMediodepagos(usuario);
            return ResponseEntity.ok(mediosdepago);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/nuevomediodepago/{id}")
    public ResponseEntity nuevomediodepago(@PathVariable("id") Usuario usuario, @Valid @RequestBody MedioDePago mediodepago) throws ParseException {
        mediodepago.setUsuario(usuario);
        mediodepagoService.createMediodepago(mediodepago);
        return ResponseEntity.ok(mediodepago);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        // Obtiene los errores de validación del objeto MethodArgumentNotValidException
        StringBuilder mensajedeerror = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach((FieldError error) -> {
            mensajedeerror.append("Campo: ").append(error.getField());
            mensajedeerror.append(", Mensaje: ").append(error.getDefaultMessage()).append("; ");
        });

        // Devuelve una respuesta con el mensaje de error y el código de estado HTTP 400 (Bad Request)
        return new ResponseEntity<>(mensajedeerror.toString(), HttpStatus.BAD_REQUEST);
    }

}
