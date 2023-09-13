/*
 * NO LICENCE 
 * Author: Ing. Nicolás Navarro Gutérrez
 */
package uy.edu.ort.obligatorio.pizzahurt.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nnavarro
 */
public final class StringUtil
{

    public static final String HASH_TYPE_SHA256 = "SHA-256";
    public static final String EMPTY_STRING = "";

    public static final Predicate<String> IS_NULL = (str) -> str == null;
    public static final Predicate<String> HAS_CONTENT = (str) -> IS_NULL.test(str) ? false : "".compareTo(str.trim()) != 0;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";

    public static String getHash(String text, String hashType)
    {
        try
        {
            MessageDigest digest = MessageDigest.getInstance(hashType);
            byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        }
        catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(StringUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return EMPTY_STRING;
    }

    public static String getHash(String text)
    {
        return getHash(text, HASH_TYPE_SHA256);
    }

    public static String getRandomString(int length, boolean withNumbers)
    {
        StringBuilder randomString = new StringBuilder();
        String sourceCharacters = CHARACTERS;

        if (withNumbers)
        {
            sourceCharacters += NUMBERS;
        }

        SecureRandom random = new SecureRandom();

        for (int i = 0; i < length; i++)
        {
            int randomIndex = random.nextInt(sourceCharacters.length());
            char randomChar = sourceCharacters.charAt(randomIndex);
            randomString.append(randomChar);
        }

        return randomString.toString();
    }
}
