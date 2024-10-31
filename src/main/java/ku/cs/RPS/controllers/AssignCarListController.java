package ku.cs.RPS.controllers;


import ku.cs.RPS.entities.Car;
import ku.cs.RPS.entities.Delivery;
import ku.cs.RPS.repository.DBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/assign-cars")
public class AssignCarListController {

    @Autowired
    private DBRepository dbRepository;

    @GetMapping("{deliveryId}")
    public String carList(@PathVariable String deliveryId,
                          @RequestParam(value="estimatedStartTime", required = false) String estimatedStartTime,
                          Model model) {

        Delivery delivery = dbRepository.findDeliveryByDeliveryId(deliveryId);
        List<Car> availableCars;

        if (estimatedStartTime != null && !estimatedStartTime.isEmpty()) {
            availableCars = dbRepository.filterCarByEstimatedStartTime(estimatedStartTime);
        } else {
            availableCars = dbRepository.findAvailableCars();
        }

        model.addAttribute("delivery", delivery);
        model.addAttribute("availableCars", availableCars);
        model.addAttribute("estimatedStartTime", estimatedStartTime); // Retain input in the form

        return "assign-car-list-view";
    }
}
