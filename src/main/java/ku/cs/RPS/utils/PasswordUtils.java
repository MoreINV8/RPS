package ku.cs.RPS.utils;

import java.util.Random;

public class PasswordUtils {

    // Pool of allowed characters (upper-case, lower-case letters, and digits)
    private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final Random random = new Random();

    // Function to generate password
    public static String generatePassword() {
        StringBuilder password = new StringBuilder(MIN_PASSWORD_LENGTH);

        // Ensure password has at least 8 characters
        for (int i = 0; i < MIN_PASSWORD_LENGTH; i++) {
            int randomIndex = random.nextInt(CHAR_POOL.length());
            password.append(CHAR_POOL.charAt(randomIndex));
        }

        return password.toString();
    }
}
