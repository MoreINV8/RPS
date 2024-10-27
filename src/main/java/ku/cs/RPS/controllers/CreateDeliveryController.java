package ku.cs.RPS.controllers;

import jakarta.validation.Valid;
import ku.cs.RPS.entities.Product;
import ku.cs.RPS.repository.DBRepository;
import ku.cs.RPS.requests.DeliveryCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/create-delivery")
@SessionAttributes("newDelivery")
public class CreateDeliveryController {

    @Autowired
    private DBRepository dbRepository;

    @GetMapping
    public String createDelivery(Model model) {
        if (!model.containsAttribute("newDelivery")) {
            model.addAttribute("newDelivery", new DeliveryCreateRequest());
        }
        model.addAttribute("newProduct", new Product());

        return "create-delivery-view";
    }

    @PostMapping
    public String createRequestHandler(
            @Valid @ModelAttribute("newDelivery") DeliveryCreateRequest newDelivery,
            BindingResult result, Model model, SessionStatus sessionStatus
    ) {

        if (result.hasErrors()) {

            model.addAttribute("newProduct", new Product());

            return "create-delivery-view";
        }

        if (!dbRepository.isExistCustomerById(newDelivery.getCustomerId())) {

            model.addAttribute("newProduct", new Product());

            result.rejectValue("customerId", "error.noExistId", "ไม่พบผู้ใช้งานนึ้");
            return "create-delivery-view";
        }

        for (Product p : newDelivery.getProducts()) {
            if (p.getProductDetail().equals("กรอกข้อมูลผิดพลาด")) {
                result.rejectValue("products", "error.itemError", "มีสินค้าที่ข้อมูลผิดพลาด");

                newDelivery.getProducts().clear();
                model.addAttribute("newProduct", new Product());

                return "create-delivery-view";
            }
        }

        String deliveryId = dbRepository.save(newDelivery);

        for (Product product : newDelivery.getProducts()) {
            product.setDeliveryId(deliveryId);
            dbRepository.save(product);
        }

        sessionStatus.setComplete();

        return "redirect:/delivery-detail/" + deliveryId;
    }

    @PostMapping("/create-item")
    public String createProductHandler(
            @ModelAttribute("newProduct") Product newProduct,
            @ModelAttribute("newDelivery") DeliveryCreateRequest newDelivery,
            Model model
    ) {

        if (newProduct.getProductCount() < 1 || newProduct.getProductDetail().isBlank()) {

            Product error = new Product();
            error.setProductCount(1);
            error.setProductDetail("กรอกข้อมูลผิดพลาด");

            newDelivery.getProducts().add(error);

        } else {

            newDelivery.getProducts().add(newProduct);

        }

        System.out.println("After added: " + newDelivery);

        model.addAttribute("newProduct", new Product());

        return "create-delivery-view";
    }

    @PostMapping("/delete-item")
    public String clearProducts(@ModelAttribute("newDelivery") DeliveryCreateRequest newDelivery, Model model) {
        newDelivery.getProducts().clear();  // Clear the list of products
        model.addAttribute("newProduct", new Product());

        return "redirect:/create-delivery";
    }
}
