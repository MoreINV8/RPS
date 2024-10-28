package ku.cs.RPS.controllers;

import ku.cs.RPS.entities.Customer;
import ku.cs.RPS.entities.Delivery;
import ku.cs.RPS.entities.Product;
import ku.cs.RPS.repository.DBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/unsent-detail")
public class UnsentDetailController {

    @Autowired
    private DBRepository dbRepository;

    @GetMapping("{id}")
    public String unsentDetail(@PathVariable String id, Model model) {
        Delivery delivery = dbRepository.findDeliveryById(id);
        List<Product> products = dbRepository.findProductsByDeliveryId(id);
        Customer customer = dbRepository.findCustomerById(delivery.getCustomerId());

        model.addAttribute("delivery", delivery);
        model.addAttribute("products", products);
        model.addAttribute("customer", customer);

        return "unsent-detail-view";
    }

    @PostMapping("{id}")
    public String sentDetail(@PathVariable String id) {
        dbRepository.updateDeliverySentDetailById(id);
        return "redirect:/home";
    }
}
