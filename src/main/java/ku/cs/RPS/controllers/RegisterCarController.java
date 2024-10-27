package ku.cs.RPS.controllers;


import jakarta.validation.Valid;
import ku.cs.RPS.entities.Car;
import ku.cs.RPS.repository.DBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/car")
public class RegisterCarController {

    @Autowired
    private DBRepository dbRepository;

    @GetMapping("/register")
    public String registerCar(Model model) {
        model.addAttribute("newCar", new Car());

        return "register-car-view";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newCar") Car newCar, BindingResult result) {

        if (result.hasErrors()) {
            return "register-car-view";
        }

        if (dbRepository.isExistCarByLicensePlate(newCar.getCarId())) {
            result.rejectValue("carId", "error.carId", "มีทะเบียนรถขนส่งนี้แล้ว");
            return "register-car-view";
        }

        if (dbRepository.isExistCarByDriverId(newCar.getDriverId())) {
            result.rejectValue("driverId", "error.driverId", "พนักงานประจำที่รถขนส่งคันอื่นแล้ว");
            return "register-car-view";
        }

        dbRepository.save(newCar);

        return "home-view";
    }
}
