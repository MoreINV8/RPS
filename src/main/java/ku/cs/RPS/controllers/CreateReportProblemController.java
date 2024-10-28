package ku.cs.RPS.controllers;

import jakarta.validation.Valid;
import ku.cs.RPS.entities.RouteProblem;
import ku.cs.RPS.repository.DBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/report-problem")
public class CreateReportProblemController {

    @Autowired
    private DBRepository dbRepository;

    @GetMapping
    public String showReportProblemForm(Model model) {
        // Create a list of 77 provinces in Thailand
        List<String> provinces = Arrays.asList(
                "กรุงเทพมหานคร", "กระบี่", "กาญจนบุรี", "กาฬสินธุ์", "กำแพงเพชร", "ขอนแก่น",
                "จันทบุรี", "ฉะเชิงเทรา", "ชลบุรี", "ชัยนาท", "ชัยภูมิ", "ชุมพร", "เชียงราย",
                "เชียงใหม่", "ตรัง", "ตราด", "ตาก", "นครนายก", "นครปฐม", "นครพนม", "นครราชสีมา",
                "นครศรีธรรมราช", "นครสวรรค์", "นนทบุรี", "นราธิวาส", "น่าน", "บึงกาฬ", "บุรีรัมย์",
                "ปทุมธานี", "ประจวบคีรีขันธ์", "ปราจีนบุรี", "ปัตตานี", "พระนครศรีอยุธยา", "พังงา",
                "พัทลุง", "พิจิตร", "พิษณุโลก", "เพชรบุรี", "เพชรบูรณ์", "แพร่", "ภูเก็ต",
                "มหาสารคาม", "มุกดาหาร", "แม่ฮ่องสอน", "ยโสธร", "ยะลา", "ร้อยเอ็ด", "ระนอง",
                "ระยอง", "ราชบุรี", "ลพบุรี", "ลำปาง", "ลำพูน", "เลย", "ศรีสะเกษ", "สกลนคร",
                "สงขลา", "สตูล", "สมุทรปราการ", "สมุทรสงคราม", "สมุทรสาคร", "สระแก้ว", "สระบุรี",
                "สิงห์บุรี", "สุโขทัย", "สุพรรณบุรี", "สุราษฎร์ธานี", "สุรินทร์", "หนองคาย", "หนองบัวลำภู",
                "อ่างทอง", "อุดรธานี", "อุตรดิตถ์", "อุทัยธานี", "อุบลราชธานี", "อำนาจเจริญ"
        );

        // Add the provinces list and a new RouteProblem object to the model
        model.addAttribute("provinces", provinces);
        model.addAttribute("report", new RouteProblem());
        return "report-problem-view";  // Your template name
    }

    @PostMapping
    public String createRequestReport(@Valid @ModelAttribute("report") RouteProblem routeProblem, BindingResult result, Model model) {
        System.out.println("test01" + routeProblem);
        dbRepository.save(routeProblem);
        return "redirect:/go-to-map";
    }
}
