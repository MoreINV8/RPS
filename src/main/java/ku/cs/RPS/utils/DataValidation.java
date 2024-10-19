package ku.cs.RPS.utils;

import java.util.regex.Pattern;

public class DataValidation {
    public static boolean phoneValidate(String phoneNumber) {
//        String pattern = "0[0-9]{9}";
        String pattern = "[0-9]{10}";
        return Pattern.matches(pattern, phoneNumber);
    }

    public static boolean emailValidate(String email) {
        String pattern = ".+@.+[.].+";
        return Pattern.matches(pattern, email);
    }
}
