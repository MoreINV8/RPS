package ku.cs.RPS.controllers;


import ku.cs.RPS.entities.Car;
import ku.cs.RPS.entities.Customer;
import ku.cs.RPS.entities.Delivery;
import ku.cs.RPS.repository.DBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/assign-delivery-detail")
public class AssignDeliveryDetailController {

    @Autowired
    private DBRepository dbRepository;

    @GetMapping("{deliveryId}")
    public String carsInDelivery(@PathVariable String deliveryId, Model model) {
        Delivery delivery = dbRepository.findDeliveryById(deliveryId);
        List<Car> cars = dbRepository.findAssignedCarsByDeliveryId(deliveryId);
        Customer customer = dbRepository.findCustomerById(delivery.getCustomerId());

//        System.out.println(delivery);
//        System.out.println(cars);
//        System.out.println(customer);

        model.addAttribute("delivery", delivery);
        model.addAttribute("cars", cars);
        model.addAttribute("customer", customer);

        return "assign-delivery-detail-view";
    }
}
