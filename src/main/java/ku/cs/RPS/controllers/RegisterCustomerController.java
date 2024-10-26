package ku.cs.RPS.controllers;

import jakarta.validation.Valid;
import ku.cs.RPS.entities.Customer;
import ku.cs.RPS.repository.DBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class RegisterCustomerController {

    @Autowired
    private DBRepository dbRepository;

    @GetMapping("/register")
    public String registerCustomer(Model model) {
        model.addAttribute("newCustomer", new Customer());

        return "register-customer-view";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newCustomer") Customer newCustomer, BindingResult result) {

        if (result.hasErrors()) {
            return "register-customer-view";
        }

        if (dbRepository.isExistCustomerByEmail(newCustomer.getEmail())) {
            result.rejectValue("email", "error.emailExist", "อีเมลนี้ใช้งานแล้ว");
            return "register-customer-view";
        }

        dbRepository.save(newCustomer);

        return "home-view";
    }
}
