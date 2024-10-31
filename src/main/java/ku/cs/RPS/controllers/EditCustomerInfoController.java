package ku.cs.RPS.controllers;

import jakarta.validation.Valid;
import ku.cs.RPS.entities.Customer;
import ku.cs.RPS.repository.DBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/edit-customer-info")
public class EditCustomerInfoController {

    @Autowired
    private DBRepository dbRepository;

    @GetMapping("/{id}")
    public String editCustomer(@PathVariable String id, Model model) {
        Customer customer = dbRepository.findCustomerById(id);

        model.addAttribute("customer", customer);

        return "edit-customer-info-view";
    }

    @PostMapping
    public String editCustomerInfo(@Valid @ModelAttribute("customer") Customer customer, BindingResult result) {

        System.out.println("Update: " + customer);

        // give a same page with error
        if (result.hasErrors())
            return "edit-customer-info-view";

        dbRepository.update(customer);

        return "redirect:/home";
    }
}
