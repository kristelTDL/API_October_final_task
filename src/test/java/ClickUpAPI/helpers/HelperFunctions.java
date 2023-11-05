package ClickUpAPI.helpers;

import java.nio.charset.Charset;
import java.util.Random;

public class HelperFunctions {
<<<<<<< HEAD
    public static String randomString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
=======
    /**
     * A function to help generate a random string.
     * Generates random numbers between 48 to 122 to match ASCII codes for letters and numbers.
     * Returns a String consisting of alphanumeric characters.
     * */
    public static String randomString() {
        int a = 48; // ASCII code where numbers start ( 0 )
        int b = 122; // ASCII code where letters end ( z )
        int stringLimit = 10; //
        Random random = new Random();

        String randomStr = random.ints(a, b + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(stringLimit) // Apply limit to the length of the final string
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return randomStr;
>>>>>>> b05fa4b (Revised final task)
    }
}
