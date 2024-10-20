package ku.cs.RPS.utils;

public class UtilityMethod {
    public static String rjust(String target, int fill, char fillChar) {

        if (target.length() >= fill) {
            return target;
        }

        StringBuilder sb = new StringBuilder();
        int paddingLength = fill - target.length();

        for (int i = 0; i < paddingLength; i++) {
            sb.append(fillChar);
        }

        sb.append(target);

        return sb.toString();
    }
}
