package kg.habittracker.project.controller;

import kg.habittracker.project.componets.RegistrationRequest;
import kg.habittracker.project.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/regitration")
@AllArgsConstructor
public class RegitrationController {

    private RegistrationService registrationService;
    public String registration(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }
}
