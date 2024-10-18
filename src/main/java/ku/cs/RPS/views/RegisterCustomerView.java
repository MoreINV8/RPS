package ku.cs.RPS.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class RegisterCustomerView {

    @GetMapping("/register")
    public String registerCustomer() {
        return "register-customer";
    }
}
