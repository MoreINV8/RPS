package ku.cs.RPS.controllers;

import ku.cs.RPS.entities.Car;
import ku.cs.RPS.entities.Delivery;
import ku.cs.RPS.entities.Notice;
import ku.cs.RPS.entities.Product;
import ku.cs.RPS.repository.DBRepository;
import ku.cs.RPS.DTO.NoticeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/assign-work")
public class AssignWorkController {

    @Autowired
    private DBRepository dbRepository;

    @GetMapping("/{deliveryId}/{carId}")
    public String assignWork(@RequestParam (value="estimatedStartTime", required = false) String estimatedStartTime ,
                             @PathVariable String deliveryId,
                             @PathVariable String carId,
                             Model model) {

        Delivery delivery = dbRepository.findDeliveryByDeliveryId(deliveryId);
        List<Product> unassignedProducts = dbRepository.findProductsByDeliveryId(deliveryId);
        Car car = dbRepository.findCarByCarId(carId);

        // Add attributes to model for the Thymeleaf view
        model.addAttribute("estimatedStartTime", estimatedStartTime);
        model.addAttribute("delivery", delivery);
        model.addAttribute("unassignedProducts", unassignedProducts);
        model.addAttribute("car", car);
        model.addAttribute("noticeRequest", new NoticeRequest());

        return "assign-work-view";
    }

    @PostMapping("/save")
    public String saveAssignment(@RequestParam("productId") List<String> productIds,
                                 @RequestParam("assignedQuantity") List<Integer> assignedQuantities,
                                 @RequestParam("deliveryId") String deliveryId,
                                 @RequestParam("carId") String carId,
                                 @ModelAttribute ("noticeRequest") NoticeRequest noticeRequest,
                                 Model model) {

        System.out.println(productIds);

        // Validate at least one product is assigned
        boolean isAssigned = assignedQuantities.stream().anyMatch(quantity -> quantity > 0);
        if (!isAssigned) {
            model.addAttribute("errorMessage", "จัดสรรงานอย่างน้อย 1 หน่วย");
            return "assign-work-view";
        }

        for (int i = 0; i < productIds.size(); i++) {
            String productId = productIds.get(i);
            int assignedQuantity = assignedQuantities.get(i);
            int productTotal = dbRepository.findProductByProductId(productId).getProductCount(); // Fetch total quantity for each product
            int remainingQuantity = productTotal - assignedQuantity;

            // Update or store remaining quantity as needed, for example:
            dbRepository.updateRemainingProductCount(productId, remainingQuantity);
        }


        // Save driver notice
        Notice notice = new Notice();
        notice.setDelivery_id(deliveryId);
        notice.setDriver_id(dbRepository.findCarByCarId(carId).getDriverId());
        notice.setCar_registration(carId);
        notice.setStart_work_date(noticeRequest.getStart_work_date().toString());

        System.out.println(notice);

        dbRepository.save(notice);


        //////////////////////// เผื่อแก้

        Delivery delivery = dbRepository.findDeliveryByDeliveryId(deliveryId);

        // Update car's usage status with the end of use time (for example, assume it's 8 hours later)
        LocalDateTime endOfUseTime = delivery.getDeliverDateTime().toLocalDate().atStartOfDay().plusHours(8);
        System.out.println(endOfUseTime);

        dbRepository.updateFinishUsed(carId, endOfUseTime);

        ////////////////////////

        return "redirect:/assign-cars/" + deliveryId;
    }

}

