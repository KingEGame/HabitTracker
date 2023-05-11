package kg.habittracker.project.controller;

import kg.habittracker.project.records.LoginRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizatonController {

    @GetMapping("/login")
    public String login(){
        return "login.html";
    }

    @PostMapping("/login")
    public String singIn(@RequestParam String username, @RequestParam String password){
        return "redirect:/";
    }
}
