package ku.cs.RPS.controllers;

import ku.cs.RPS.entities.Notice;
import ku.cs.RPS.repository.DBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/driver-home-list-job")
public class DriverHomeListJobController {

    @Autowired
    private DBRepository dbRepository;

    // Query every notice of every drivers
    /*
    @GetMapping
    public String driver(Model model, String id){
        model.addAttribute("notices", dbRepository.findJobList());
        return "driver-home-list-job";
    }*/

    // Query many notices per one driver
    @GetMapping
    public String driver(Model model, String id) {
        id = "e000000003";  // Static ID for testing
        List<Notice> notices = dbRepository.findJobListByEmployeeId(id);
        System.out.println(notices);
        model.addAttribute("notices", notices);
        return "driver-home-list-job-view";
    }
}
