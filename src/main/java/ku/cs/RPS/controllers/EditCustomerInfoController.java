package ku.cs.RPS.controllers;

import jakarta.validation.Valid;
import ku.cs.RPS.entities.Customer;
import ku.cs.RPS.repository.DBConnect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/edit-customer-info")
public class EditCustomerInfoController {

    @Autowired
    private DBConnect dbConnect;

    @GetMapping("/{id}")
    public String editCustomer(@PathVariable String id, Model model) {
        Customer customer = dbConnect.findCustomerById(id);

        model.addAttribute("customer", customer);

        return "edit-customer-info-view";
    }

    @PostMapping("{id}")
    public String editCustomerInfo(@Valid Customer customer, BindingResult result) {

        // give a same page with error
        if (result.hasErrors())
            return "edit-customer-info-view";

        dbConnect.update(customer);

        return "redirect:/home";
    }
}
