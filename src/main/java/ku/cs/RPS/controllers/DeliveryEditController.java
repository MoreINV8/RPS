package ku.cs.RPS.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import ku.cs.RPS.DTO.DeliveryEditRequest;
import ku.cs.RPS.entities.Product;
import ku.cs.RPS.repository.DBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/delivery-edit")
@SessionAttributes("delivery")
public class DeliveryEditController {

    @Autowired
    private DBRepository dbRepository;

    @GetMapping("/{id}")
    public String deliveryEdit(@PathVariable String id, Model model) {
        if (!model.containsAttribute("delivery")) {
            DeliveryEditRequest delivery = new DeliveryEditRequest(
                    dbRepository.findDeliveryById(id),
                    dbRepository.findProductsByDeliveryId(id)
            );

            model.addAttribute("delivery", delivery);
        }
        model.addAttribute("newProduct", new Product());

        return "delivery-edit-view";
    }

    @PostMapping
    public String insertRequestHandler(
            @Valid @ModelAttribute("delivery") DeliveryEditRequest delivery,
            BindingResult result, Model model, HttpSession session
    ) {

        if (result.hasErrors()) {

            model.addAttribute("newProduct", new Product());

            return "delivery-edit-view";
        }

        for (Product p : delivery.getProducts()) {
            if (p.getProductDetail().equals("กรอกข้อมูลผิดพลาด")) {
                result.rejectValue("products", "error.itemError", "มีสินค้าที่ข้อมูลผิดพลาด");

                delivery.getProducts().clear();
                model.addAttribute("newProduct", new Product());

                return "delivery-edit-view";
            }
        }

        dbRepository.update(delivery);

        for (Product product : delivery.getProducts()) {
            product.setDeliveryId(delivery.getDeliveryId());
            dbRepository.save(product);
        }

        session.removeAttribute("delivery");

        return "redirect:/delivery-detail/" + delivery.getDeliveryId();
    }

    @PostMapping("/insert-product")
    public String createProductHandler(
            @ModelAttribute("delivery") DeliveryEditRequest delivery,
            @ModelAttribute("newProduct") Product newProduct,
            Model model
    ) {

        if (newProduct.getProductCount() < 1 || newProduct.getProductDetail().isBlank()) {

            Product error = new Product();
            error.setProductCount(1);
            error.setProductDetail("กรอกข้อมูลผิดพลาด");

            delivery.getProducts().add(error);

        } else {

            delivery.getProducts().add(newProduct);

        }

        System.out.println("After added: " + delivery);

        model.addAttribute("newProduct", new Product());

        return "delivery-edit-view";
    }

    @PostMapping("/reset-item")
    public String resetHandle(
            @ModelAttribute("delivery") DeliveryEditRequest delivery,
            Model model
    ) {
        delivery.resetInput();
        delivery.getProducts().clear();
        model.addAttribute("newProduct", new Product());
        return "delivery-edit-view";
    }

    @PostMapping("/delete-product")
    public String deleteHandle(
            @ModelAttribute("delivery") DeliveryEditRequest delivery,
            HttpSession session
    ) {
        dbRepository.updateDeliveryFKToNullById(delivery.getDeliveryId());
        dbRepository.deleteDeliveryById(delivery.getDeliveryId());

        for (Product p : delivery.getProducts()) {
            dbRepository.updateProductFKToNullById(p.getId());
            dbRepository.deleteProductById(p.getId());
        }

        session.removeAttribute("delivery");

        return "redirect:/home";
    }
}
