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

    @GetMapping("signin.html") 
    public String signin() {
        return "signin";
    }

    @GetMapping("index.html") 
    public String index() {
        return "index";
    }

    @GetMapping("about.html") 
    public String about() {
        return "about";
    }

    @GetMapping("contact.html") 
    public String contact() {
        return "contact";
    }

    @GetMapping("team.html") 
    public String team() {
        return "team";
    }

    @GetMapping("training.html") 
    public String training() {
        return "training";
    }

    @GetMapping("logWorkout.html") 
    public String logWorkout() {
        return "logWorkout";
    }
    @GetMapping("findWorkout.html") 
    public String findWorkout() {
        return "findWorkout";
    }

    @GetMapping("summary.html") 
    public String summary() {
        return "summary";
    }
    @GetMapping("summaryButton.html") 
    public String summaryButton() {
        return "summaryButton";
    }

    @GetMapping("musclegroups.html") 
    public String musclegroups() {
        return "musclegroups";
    }

    @GetMapping("nutrition.html") 
    public String nutrition() {
        return "nutrition";
    }

    @GetMapping("bodyweight.html") 
    public String bodyweight() {
        return "bodyweight";
    }
    @GetMapping("forgot.html") 
    public String forgot() {
        return "forgot";
    }
    @GetMapping("signout.html") 
    public String signout() {
        return "signout";
    }
}
