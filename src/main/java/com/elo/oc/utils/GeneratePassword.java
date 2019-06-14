package com.elo.oc.utils;

import java.nio.charset.Charset;
import java.util.Random;

/**
 * <p>Class for generating passwords when a user clicks the reset password button</p>
 */
public class GeneratePassword {
    /**
     * <p>Generates an alphanumeric password to be sent by email to user.</p>
     * @param n number of characters the string should contain
     * @return new password
     * @see com.elo.oc.controller.UserController
     * @see com.elo.oc.service.EmailService
     */
    public static String getAlphaNumericString(int n) {
        byte[] array = new byte[256];
        new Random().nextBytes(array);
        String randomString = new String(array, Charset.forName("UTF-8"));
        // Create a StringBuffer to store the result
        StringBuffer r = new StringBuffer();
        // remove all spacial char
        String AlphaNumericString
                = randomString
                .replaceAll("[^A-Za-z0-9]", "");
        // Append first 20 alphanumeric characters
        // from the generated random String into the result
        for (int k = 0; k < AlphaNumericString.length(); k++) {

            if (Character.isLetter(AlphaNumericString.charAt(k))
                    && (n > 0)
                    || Character.isDigit(AlphaNumericString.charAt(k))
                    && (n > 0)) {

                r.append(AlphaNumericString.charAt(k));
                n--;
            }
        }
        // return the resultant string
        return r.toString();
    }
}
