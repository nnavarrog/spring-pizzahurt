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
package uy.edu.ort.obligatorio.pizzahurt.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public final class AmountUtil
{

    public static final String CURRENCY_UYU_SYMBOL = "$U";
    public static final String CURRENCY_USD_SYMBOL = "U$S";
    public static final String CURRENCY_DOLLAR_SYMBOL = "$";

    public static String getFormatedPrice(BigDecimal amount, Object currSymbol)
    {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setCurrencySymbol(currSymbol.toString());
        DecimalFormat df = new DecimalFormat("#0.00", symbols);
        return df.format(amount);
    }

    public static String getFormatedPrice(BigDecimal amount)
    {
        return getFormatedPrice(amount, (Object)CURRENCY_DOLLAR_SYMBOL);
    }

    public static String getFormatedPrice(BigDecimal amount, Object currSymbol, String label, String separator)
    {
        String formatedPrice = getFormatedPrice(amount, currSymbol);
        return label + separator + formatedPrice;
    }

    public static String getFormatedPrice(BigDecimal amount, Object currSymbol, String label)
    {
        return getFormatedPrice(amount, currSymbol, label, " - ");
    }
    
    public static String getFormatedPrice(BigDecimal amount, String label)
    {
        return getFormatedPrice(amount, CURRENCY_DOLLAR_SYMBOL , label, " - ");
    }
}
