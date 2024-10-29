package ku.cs.RPS.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

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

    public static String getEmployeeId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            // Get the authorities granted to the current user
            List<? extends GrantedAuthority> authorities = (List<? extends GrantedAuthority>) authentication.getAuthorities();

            // Check if the user has a specific authority
            return authorities.get(1).toString();

        }

        return null;
    }
}
