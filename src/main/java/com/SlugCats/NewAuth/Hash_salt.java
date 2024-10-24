package com.SlugCats.NewAuth;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;


public class Hash_salt {
    /**
     * Generates a new random salt to be used for password hashing.
     *
     * @return a Base64 encoded string representing the generated salt.
     */
    public static String getSalt() {//generate a salt for user
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }
    /**
     * Generates a hashed version of the provided password using the given salt.
     * Combines the salt and password before hashing using the SHA-256 algorithm.
     *
     * @param password The password to hash.
     * @param salt The salt to use for hashing.
     * @return The hashed password in hexadecimal format.
     */
    public static String generateHash(String password,String salt) {//hash and salt the password

        String hashedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update((salt + password).getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hashedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashedPassword;
    }
}
