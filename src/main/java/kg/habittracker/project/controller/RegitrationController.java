package kg.habittracker.project.controller;

import kg.habittracker.project.DTO.UserDTO;
import kg.habittracker.project.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@AllArgsConstructor
public class RegitrationController {

    private RegistrationService registrationService;

    @GetMapping("/registration")
    public String page(){
        return "registration.html";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute UserDTO request) {
        registrationService.register(request);
        return "confirmation.html";
    }
}
