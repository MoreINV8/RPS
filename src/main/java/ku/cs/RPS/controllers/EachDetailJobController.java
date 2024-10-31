package ku.cs.RPS.controllers;

import jakarta.validation.Valid;
import ku.cs.RPS.entities.Employee;
import ku.cs.RPS.entities.Notice;
import ku.cs.RPS.repository.DBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/each-job-info")
public class EachDetailJobController {

    @Autowired
    private DBRepository dbRepository;

    @GetMapping("/{id}")
    public String showJobList(@PathVariable String id, Model model){
        Notice notice = dbRepository.findNoticeById(id);
        model.addAttribute("callNotice", notice);
        return "show-job-info-view";
    }

    @PostMapping("{id}")
    public String updateEachNoticeInfo(@Valid @ModelAttribute("callNotice") Notice callNotice) {
        dbRepository.update(callNotice);
        return "redirect:/driver-home-list-job";
    }
}
