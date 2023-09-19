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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uy.edu.ort.obligatorio.pizzahurt.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 *
 * @author jobs
 */
public class creditCardValidator implements ConstraintValidator<creditCard, String> {

    private int sum;
    
    @Override
    public void initialize(creditCard constraintAnnotation) {
        // TODO Auto-generated method stub
    }
    
    //Algoritmo de Luhn
    @Override
    public boolean isValid(String cardNumber, ConstraintValidatorContext context) {
        Boolean valida = Boolean.TRUE;

        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int digit = Integer.parseInt(cardNumber.substring(i, i + 1));

            if ((cardNumber.length() - i) % 2 == 0) {
                digit = doubleAndSumDigits(digit);
            }

            sum += digit;
        }
        
        if(sum % 10 != 0){
        valida = Boolean.FALSE;
        }
        
        return valida;
    }
    
    private static int doubleAndSumDigits(int digit) {
    int ret = digit * 2;

    if (ret > 9) {
        ret = digit - 9;
    }

    return ret;
}

}
