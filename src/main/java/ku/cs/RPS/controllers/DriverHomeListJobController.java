package ku.cs.RPS.controllers;

import ku.cs.RPS.entities.Notice;
import ku.cs.RPS.repository.DBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public String driver(Model model) throws IllegalAccessException {
        String id = getEmployeeId();

        if (id == null) {
            throw new IllegalAccessException();
        }

        List<Notice> notices = dbRepository.findJobListByEmployeeId(id);
        System.out.println(notices);
        model.addAttribute("notices", notices);
        return "driver-home-list-job-view";
    }

    private String getEmployeeId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            // Get the authorities granted to the current user
            List<? extends GrantedAuthority> authorities = (List<? extends GrantedAuthority>) authentication.getAuthorities();

            // Check if the user has a specific authority
            return authorities.get(1).toString();

        }

        return null;
    }
}
