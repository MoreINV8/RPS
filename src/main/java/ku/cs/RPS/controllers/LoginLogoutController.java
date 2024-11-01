package ku.cs.RPS.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginLogoutController {

    @GetMapping("/login")
    public String login() {
        return "login-view";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Authentication auth) {
        if (auth != null) {
            new SecurityContextLogoutHandler()
                    .logout(request, response, auth);
        }

        return "redirect:/login?logout";
    }
}
