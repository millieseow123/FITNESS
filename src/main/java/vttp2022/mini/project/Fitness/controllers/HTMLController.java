package vttp2022.mini.project.Fitness.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HTMLController {
    
    @GetMapping("signup.html") 
    public String signup() {
        return "signup";
    }

    //forget password
}
