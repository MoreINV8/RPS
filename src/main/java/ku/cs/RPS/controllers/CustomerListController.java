package ku.cs.RPS.controllers;

import ku.cs.RPS.repository.DBConnect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customers")
public class CustomerListController {
    @Autowired
    private DBConnect dbConnect;

    @GetMapping
    public String customers(Model model) {
        model.addAttribute("customers", dbConnect.findCustomers());

        return "customer-list-view";
    }
}
