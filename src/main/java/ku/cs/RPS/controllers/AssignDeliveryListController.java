package ku.cs.RPS.controllers;


import ku.cs.RPS.entities.Car;
import ku.cs.RPS.repository.DBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/assign-deliveries")
public class AssignDeliveryListController {

    @Autowired
    private DBRepository dbRepository;

    @GetMapping
    public String deliveryList(Model model) {
        model.addAttribute("assignedDeliveries", dbRepository.getDeliveriesDistinctByAlreadyAssignAmount());
        model.addAttribute("unassignedDeliveries", dbRepository.getDeliveriesDistinctByNotAssignAmount());

        return "assign-delivery-list-view";
    }
}
