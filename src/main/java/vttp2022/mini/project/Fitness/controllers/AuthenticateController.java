package vttp2022.mini.project.Fitness.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vttp2022.mini.project.Fitness.models.User;
import vttp2022.mini.project.Fitness.services.FitnessException;
import vttp2022.mini.project.Fitness.services.UserService;

import static vttp2022.mini.project.Fitness.models.Utilities.*;

@Controller
@RequestMapping
public class AuthenticateController {

    @Autowired
    private UserService userSvc;

    @PostMapping("/signup")
    public ModelAndView signup(@RequestBody MultiValueMap<String, String> form) {

        User user = convertSignUp(form);
        ModelAndView mvc = new ModelAndView();
        try {
            userSvc.addNewUser(user);
            mvc.addObject("signupSuccess",
                    "Sign up successful with email %s. Please proceed to login.".formatted(user.getEmail()));
        } catch (FitnessException e) {
            mvc.addObject("error", "Error:  %s".formatted(e.getReason()));
            mvc.setStatus(HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        mvc.setViewName("index");
        return mvc;
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestBody MultiValueMap<String, String> form, HttpSession sess) {
        User user = convertLogin(form);

        ModelAndView mvc = new ModelAndView();
        if (!userSvc.authenticate(user.getEmail(), user.getPassword())) {
            mvc.setViewName("error");
            mvc.setStatus(HttpStatus.FORBIDDEN);
        } else {
            sess.setAttribute("username", user.getEmail());
            mvc.setStatus(HttpStatus.OK);
            mvc.setViewName("logWorkout");
        }
        return mvc;
    }

    @GetMapping("/signout")
    public String signout(HttpSession sess) {

        sess.invalidate();
        return "signOutSuccess";

    }
}
