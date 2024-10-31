package ku.cs.RPS.controllers;

import ku.cs.RPS.repository.DBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customers")
public class CustomerListController {
    @Autowired
    private DBRepository dbRepository;

    @GetMapping
    public String customers(Model model) {
        model.addAttribute("customers", dbRepository.findCustomers());

        return "customer-list-view";
    }
}
