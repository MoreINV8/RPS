package ku.cs.RPS.controllers;

import jakarta.validation.Valid;
import ku.cs.RPS.DTO.RegisterEmployeeRequest;
import ku.cs.RPS.entities.Employee;
import ku.cs.RPS.repository.DBRepository;
import ku.cs.RPS.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee/register")
public class RegisterEmployeeController {

    @Autowired
    private DBRepository dbRepository;

    @GetMapping
    public String employeeRegister(Model model) {
        model.addAttribute("newEmployee", new RegisterEmployeeRequest());
        return "register-employee-view";
    }

    @PostMapping
    public String registerHandler(
            @Valid @ModelAttribute("newEmployee") RegisterEmployeeRequest newEmployee,
            BindingResult result, Model model
    ) {

        if (result.hasErrors()) {
            return "register-employee-view";
        }

        if (dbRepository.isExistEmployeeByEmail(newEmployee.getEmployeeEmail())) {
            result.rejectValue("employeeEmail", "error.emailExists", "มีพนักงานใช้งานอีเมลนี้แล้ว");
        }

        String password = PasswordUtils.generatePassword();
        model.addAttribute("generatedPassword", password);

        Employee e = new Employee(newEmployee);
        e.setEmployeePassword(password);

        dbRepository.save(e);

        return "register-employee-view";
    }
}
