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
    public String customers(Model model) {
        model.addAttribute("adminEdit", dbRepository.findEmployees());
        return "show-employee-list-view";
    }

//    @GetMapping("/search")
//    public String searchEmployees(@RequestParam("query") String query, Model model) {
//        List<Employee> employees = dbRepository.findEmployees();
//        model.addAttribute("adminEdit", employees);
//        return "show-employee-list-view"; // return the view with updated employee list
//    }
//
//    // New endpoint for AJAX search suggestions
//    @GetMapping("/search/suggestions")
//    @ResponseBody
//    public List<String> getEmployeeSuggestions(@RequestParam("query") String query) {
//        return Collections.singletonList(dbRepository.findEmployees().toString());  // return names as JSON for suggestions
//    }
}
