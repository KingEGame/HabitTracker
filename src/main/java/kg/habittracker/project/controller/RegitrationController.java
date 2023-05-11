package kg.habittracker.project.controller;

import kg.habittracker.project.records.RegistrationRequest;
import kg.habittracker.project.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegitrationController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/registration")
    public String page(){
        return "registration.html";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute RegistrationRequest request){
        return registrationService.register(request);
    }
}
