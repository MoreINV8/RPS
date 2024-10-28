package ku.cs.RPS.controllers;

import ku.cs.RPS.DTO.BillResponse;
import ku.cs.RPS.repository.DBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bill-detail")
public class BillDetailController {

    @Autowired
    private DBRepository dbRepository;

    @GetMapping("{deliveryId}")
    public String billDetail(@PathVariable String deliveryId, Model model) {
        System.out.println(deliveryId);

        BillResponse response = new BillResponse();
        response.setBill(dbRepository.findBillByDeliveryId(deliveryId));
        response.setDc(dbRepository.findDeliveryCustomerByDeliveryId(deliveryId));
        response.setNecs(dbRepository.findNoticeEmployeeCarByDeliveryId(deliveryId));

        model.addAttribute("billResponse", response);

        return "bill-detail-view";
    }
}
