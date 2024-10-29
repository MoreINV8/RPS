package ku.cs.RPS.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Enumeration;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String home(HttpServletRequest request) {
        clearSession(request);

        return "home-view";
    }

    private void clearSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String keepAttribute = "SPRING_SECURITY_CONTEXT"; // Name of the attribute you want to keep

// Get all attribute names
        Enumeration<String> attributeNames = session.getAttributeNames();

        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();

            System.out.println(attributeName);

            // Remove all attributes except the specified one
            if (!attributeName.equals(keepAttribute)) {
                session.removeAttribute(attributeName);
            }
        }
    }
}
