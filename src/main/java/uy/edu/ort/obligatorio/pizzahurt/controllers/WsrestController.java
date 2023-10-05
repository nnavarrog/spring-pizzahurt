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
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.text.ParseException;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import uy.edu.ort.obligatorio.pizzahurt.constraints.MedioPago;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.MedioDePago;
import uy.edu.ort.obligatorio.pizzahurt.model.entities.Usuario;
import uy.edu.ort.obligatorio.pizzahurt.service.MediodepagoService;
import uy.edu.ort.obligatorio.pizzahurt.service.UsuarioService;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class WsrestController {

    private UsuarioService usuarioservice;
    private MediodepagoService mediodepagoService;
    private AuthenticationProvider authProvider;

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody Usuario usuario, HttpSession session) {

        try {
            Authentication auth = authProvider.authenticate(new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getPassword()));
            return ResponseEntity.ok().body("sesionid: " + session.getId());
        }catch(Exception ex)
        {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/registrarse")
    public ResponseEntity registrarse(@Valid @RequestBody Usuario usuario) {
        usuarioservice.crearUsuario(usuario);
        return ResponseEntity.created(null).body(usuario);
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

    @PostMapping(value = "/nuevomediodepago/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MedioDePago> nuevomediodepago(@PathVariable("id") Usuario usuario, @Validated(MedioPago.class) @RequestBody MedioDePago mediodepago) throws ParseException {
        mediodepago.setUsuario(usuario);
        mediodepagoService.createMediodepago(mediodepago);
        return ResponseEntity.created(null).body(mediodepago);
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
