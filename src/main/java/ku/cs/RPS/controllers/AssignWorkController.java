package ku.cs.RPS.controllers;

import ku.cs.RPS.entities.Car;
import ku.cs.RPS.entities.Delivery;
import ku.cs.RPS.entities.Product;
import ku.cs.RPS.repository.DBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/assign-work")
public class AssignWorkController {

    @Autowired
    private DBRepository dbRepository;

    @GetMapping("/{deliveryId}/{carId}")
    public String assignWork(@PathVariable String deliveryId, @PathVariable String carId, Model model) {
        // Fetch delivery details by deliveryId
        Delivery delivery = dbRepository.findDeliveryByDeliveryId(deliveryId);

        // Fetch list of products for the specific deliveryId
        List<Product> unassignedProducts = dbRepository.getUnassignedProductsByDeliveryId(deliveryId);

        // Fetch car details by carId
        Car car = dbRepository.findCarByDriverId(carId);

        // Add attributes to model for the Thymeleaf view
        model.addAttribute("delivery", delivery);
        model.addAttribute("unassignedProducts", unassignedProducts);
        model.addAttribute("car", car);

        return "assign-work-view";
    }

    @PostMapping("/save")
    public String saveAssignment(@RequestParam("productId") List<String> productIds,
                                 @RequestParam("assignedQuantity") List<Integer> assignedQuantities,
                                 @RequestParam("deliveryId") String deliveryId,
                                 @RequestParam("carId") String carId) {
        // Iterate through each product and assign the quantity for delivery
        for (int i = 0; i < productIds.size(); i++) {
            String productId = productIds.get(i);
            int quantity = assignedQuantities.get(i);
            dbRepository.assignProductToCar(deliveryId, carId, productId, quantity);
        }

        return "redirect:/assign-work/" + deliveryId + "/" + carId;
    }
}

