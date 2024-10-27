package ku.cs.RPS.controllers;

import ku.cs.RPS.repository.DBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/delivery-list")
public class DeliveryListController {

    @Autowired
    private DBRepository dbRepository;

    @GetMapping
    public String deliveryList(Model model) {

        model.addAttribute("deliveries", dbRepository.findUncreatedDeliveries());
        return "delivery-list-view";
    }
}
