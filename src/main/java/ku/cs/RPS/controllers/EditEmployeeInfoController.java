package ku.cs.RPS.controllers;

import jakarta.validation.Valid;
import ku.cs.RPS.entities.Employee;
import ku.cs.RPS.repository.DBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/edit-employee-info")
public class EditEmployeeInfoController {

    @Autowired
    private DBRepository dbRepository;

    @GetMapping("/{id}")
    public String editEmployee(@PathVariable String id, Model model) {
        System.out.println("TEstsss" + id);
        Employee adminEditEmp = dbRepository.findEmployeeById(id);
        model.addAttribute("adminEditEmp", adminEditEmp);
        return "edit-employee-info-view";
    }

    @PostMapping("{id}")
    public String updateEmployeeInfo(@Valid @ModelAttribute Employee adminEditEmp, BindingResult result) {
        System.out.println("UPDATE เด้ออ้าย");
        System.out.println(Integer.toString(2) + adminEditEmp);
        // give a same page with error
        if (result.hasErrors())
            return "edit-employee-info-view";

        dbRepository.update(adminEditEmp);
        return "redirect:/adminEditEmployee";
    }
}
