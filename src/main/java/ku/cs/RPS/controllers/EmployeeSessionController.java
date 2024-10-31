package ku.cs.RPS.controllers;

import jakarta.servlet.http.HttpSession;
import ku.cs.RPS.entities.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class EmployeeSessionController {

    @ModelAttribute("user")
    public Employee getUser(HttpSession session) {
        return (Employee) session.getAttribute("user");
    }
}
