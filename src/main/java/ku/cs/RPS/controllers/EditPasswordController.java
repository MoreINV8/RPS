package ku.cs.RPS.controllers;

import jakarta.validation.Valid;
import ku.cs.RPS.DTO.EditPasswordRequest;
import ku.cs.RPS.entities.Employee;
import ku.cs.RPS.repository.DBRepository;
import ku.cs.RPS.utils.UtilityMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/edit-password")
public class EditPasswordController {

    @Autowired
    private DBRepository dbRepository;

    @GetMapping
    public String editPasswordPage(Model model) {
        model.addAttribute("editPassword", new EditPasswordRequest());

        return "edit-password-view";
    }

    @PostMapping
    public String editPasswordHandler(
            @Valid @ModelAttribute("editPassword") EditPasswordRequest editPassword,
            BindingResult result, Model model
    ) throws IllegalAccessException {
        if (result.hasErrors()) {
            return "edit-password-view";
        }

        String id = UtilityMethod.getEmployeeId();

        if (id == null) {
            throw new IllegalAccessException();
        }

        Employee employee = dbRepository.findEmployeeById(id);

        if (!employee.getEmployeePassword().equals(editPassword.getOldPassword())) {
            result.rejectValue("oldPassword", "error.wrongPass", "รหัสผ่านเก่าไม่ถูกต้อง");
        } else if (!editPassword.getNewPassword().equals(editPassword.getConfirmPassword())) {
            result.rejectValue("confirmPassword", "error.wrongPassword", "รหัสผ่านไม่ตรงกัน");
        } else {
            model.addAttribute("success", true);

            dbRepository.updateEmployeePassword(id, editPassword.getNewPassword());
        }

        return "edit-password-view";

    }
}
