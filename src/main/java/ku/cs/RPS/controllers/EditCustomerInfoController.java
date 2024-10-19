package ku.cs.RPS.controllers;

import jakarta.validation.Valid;
import ku.cs.RPS.Exception.SomeThingWrongException;
import ku.cs.RPS.entities.Customer;
import ku.cs.RPS.repository.DBConnect;
import ku.cs.RPS.utils.DataValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/edit-customer-info")
public class EditCustomerInfoController {

    @Autowired
    private DBConnect dbConnect;

    @GetMapping("/{id}")
    public String editCustomer(@PathVariable String id, Model model) throws SomeThingWrongException {
        Customer customer = dbConnect.findCustomerById(id);

        model.addAttribute("customer", customer);

        return "edit-customer-info-view";
    }

    @PostMapping("{id}")
    public String editCustomerInfo(@Valid Customer customer, BindingResult result) {
        System.out.println(customer);

        if (result.hasErrors())
            return "edit-customer-info-view";


        return "redirect:/home";
    }

    private boolean isNotNull(Customer customer, Model model) {
        boolean isNotNull = true;
        if (customer.getFirstName() == null) {
            model.addAttribute("wrongFormatFirstName", "ข้อมูลต้องไม่ว่าง");
            isNotNull = false;
        }
        if (customer.getLastName() == null) {
            model.addAttribute("wrongFormatLastName", "ข้อมูลต้องไม่ว่าง");
            isNotNull = false;
        }
        if (customer.getPhoneNumber() == null) {
            model.addAttribute("wrongFormatPhoneNumber", "ข้อมูลต้องไม่ว่าง");
            isNotNull = false;
        }
        if (customer.getAddress() == null) {
            model.addAttribute("wrongFormatAddress", "ข้อมูลต้องไม่ว่าง");
            isNotNull = false;
        }
        if (customer.getEmail() == null) {
            model.addAttribute("wrongFormatEmail", "ข้อมูลต้องไม่ว่าง");
            isNotNull = false;
        }
        return isNotNull;
    }

    private boolean isValid(Customer customer, Model model) {
        boolean isValid = true;
        if (!DataValidation.phoneValidate(customer.getPhoneNumber())) {
            model.addAttribute("wrongFormatPhoneNumber", "เบอร์มือถือต้องเป็นตัวเลข 10 ตัว");
            isValid = false;
        }
        if (!DataValidation.emailValidate(customer.getEmail())) {
            model.addAttribute("wrongFormatEmail", "รูปแบบไม่ตรงกับที่อยู่อีเมล");
            isValid = false;
        }
        return isValid;
    }
}
