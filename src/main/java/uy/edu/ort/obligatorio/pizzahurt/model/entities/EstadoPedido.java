/**
 * NO LICENCE
 * 
 * Proyecto obligatorio final.
 * Curso: Desarrollo de aplicaciones con Spring / Spring Boot
 * Universidad ORT
 * 
 * Docente: Juan Larrayoz
 * 
 * Authors: 
 *      Fourment, Juan
 *      Navarro Gutérrez, Nicolás
 *      Ortuzar, Martín
 */
package uy.edu.ort.obligatorio.pizzahurt.model.entities;

public enum EstadoPedido
{
    NUEVO,
    PENDIENTE_PAGO,
    PENDIENTE_ENVIO,
    ENVIADO,
    RECHAZADO,
    ENTREGADO
}
