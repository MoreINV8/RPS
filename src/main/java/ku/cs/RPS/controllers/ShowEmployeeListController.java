package ku.cs.RPS.controllers;

import ku.cs.RPS.entities.Employee;
import ku.cs.RPS.repository.DBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/adminEditEmployee")
public class ShowEmployeeListController {
    @Autowired
    private DBRepository dbRepository;


    @GetMapping
    public String employee(Model model) {
        model.addAttribute("adminEdit", dbRepository.findEmployees());
        return "show-employee-list-view";
    }
}
