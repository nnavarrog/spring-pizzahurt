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
package uy.edu.ort.obligatorio.pizzahurt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.function.Supplier;

/**
 * @author nnavarro
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Amount
{

    public static final String DEFAULT_CURRENCY = "UYU";
    public static final Supplier<Amount> ZERO_AMOUNT = () -> new Amount(BigDecimal.ZERO, Currency.getInstance(DEFAULT_CURRENCY));

    private BigDecimal value;
    private Currency currency;

    @Override
    public String toString()
    {
        return toString(true);
    }

    /**
     * If separateSimbol is true, then returns a String where currency code 
     * is separated by a white space from amount value.
     * 
     * @param separateSimbol
     * @return
     */
    public String toString(boolean separateSimbol)
    {
        return separateSimbol
                ? (new StringBuilder()
                        .append(currency.getSymbol())
                        .append(" ")
                        .append(value.toString()).toString())
                : (new StringBuilder().append(currency.getSymbol()).append(value.toString()).toString());
    }

    /**
     * Amount param must be a String with integer format.
     * 
     * @param amount
     * @param currenyCode
     */
    public Amount(String amount, String currenyCode)
    {
        this(new BigDecimal(amount), Currency.getInstance(currenyCode));
    }

    /**
     * Return new amount as result of sum of this and other
     * 
     * @param other
     * @return Amount
     */
    public Amount add(Amount other)
    {
        chechCurrency(other);
        BigDecimal result = value.add(other.value);
        return new Amount(result, currency);
    }

    /**
     * Return new amount as result of substraction of this and other
     * 
     * @param other
     * @return Amount
     */
    public Amount substract(Amount other)
    {
        chechCurrency(other);
        BigDecimal result = value.subtract(other.value);
        return new Amount(result, currency);
    }

    /**
     * Return new amount as result of multiply of this and double value
     * 
     * @param value
     * @return Amount
     */
    public Amount multiply(double value)
    {
        BigDecimal result = this.value.multiply(new BigDecimal(value));
        return new Amount(result, currency);
    }

    /**
     * This function returns new converted amount from this.currency to 
     * target currency applying a conversionRate.
     * 
     * @param targetCurrency
     * @param conversionRate
     * @return
     */
    public Amount convertTo(Currency targetCurrency, BigDecimal conversionRate)
    {
        BigDecimal convertedValue = value.multiply(conversionRate);
        return new Amount(convertedValue, targetCurrency);
    }

    /**
     * Return local currency code. If currency is null, then return an 
     * empty string.
     * 
     * @return String
     */
    public String getCurrencyCode()
    {
        return this.currency != null ? this.currency.getCurrencyCode() : "";
    }

    protected void chechCurrency(Amount other)
    {
        if (!currency.equals(other.currency))
        {
            throw new IllegalArgumentException("Las monedas no coinciden");
        }
    }
}
