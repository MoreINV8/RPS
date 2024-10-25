package ku.cs.RPS.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin-home")
public class AdminHomeController {

    @GetMapping
    public String adminHome(Model model) {
        model.addAttribute("adminHome", "You are admin, right?");
        return "admin-home-view";
    }
}