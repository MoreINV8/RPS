package ku.cs.RPS.controllers;

import ku.cs.RPS.entities.RouteProblem;
import ku.cs.RPS.repository.DBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/go-to-map")
public class MapController {

    @Autowired
    private DBRepository dbRepository;

    @GetMapping
    public String showReportMap(@RequestParam(value = "province", required = false) String province, Model model) {
        // Get list of unique provinces for the dropdown
        List<String> provinces = dbRepository.findAllProvinces();
        model.addAttribute("provinces", provinces);

        // If a province is selected, retrieve the route problems for that province
        if (province != null && !province.isEmpty()) {
            List<RouteProblem> routeProblems = dbRepository.showAllProblemRoute(province);
            model.addAttribute("routeProblems", routeProblems);
            model.addAttribute("selectedProvince", province);
        }

        return "report-map-view";
    }
}
