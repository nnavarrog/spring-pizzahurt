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
package uy.edu.ort.obligatorio.pizzahurt.exceptions;

public class EntidadNoExiste extends Exception
{

    public EntidadNoExiste()
    {
        super("Endidad no existe");
    }

    public EntidadNoExiste(String message)
    {
        super(message);
    }
    
}
