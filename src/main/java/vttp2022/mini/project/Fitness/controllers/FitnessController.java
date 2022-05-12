package vttp2022.mini.project.Fitness.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vttp2022.mini.project.Fitness.models.Tracker;
import vttp2022.mini.project.Fitness.models.User;
import vttp2022.mini.project.Fitness.services.FitnessException;
import vttp2022.mini.project.Fitness.services.UserService;
import vttp2022.mini.project.Fitness.services.TrackerService;

import static vttp2022.mini.project.Fitness.models.Utilities.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
public class FitnessController {

    @Autowired
    private UserService userSvc;

    @Autowired
    private TrackerService trackerSvc;

    @PostMapping("/signup")
    public ModelAndView signup(@RequestBody MultiValueMap<String, String> form) {
        User user = convertSignUp(form);
        ModelAndView mvc = new ModelAndView();
        try {
            userSvc.addNewUser(user);
            mvc.addObject("signupSuccess",
                    "Sign up success with email %s. Please proceed to login.".formatted(user.getEmail()));
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
            mvc.setViewName("logExercises");
        }
        return mvc;
    }

    @PostMapping("/log") //maybe save this to wger?
    public ModelAndView logExercise(@RequestBody MultiValueMap<String, String> form) {
        Tracker tracker = convertExercise(form);

        ModelAndView mvc = new ModelAndView();
        try {
            trackerSvc.addNewExercise(tracker);
            mvc.addObject("logSuccess",
                    "Sucessfully added exercise");
        } catch (FitnessException e) {
            mvc.addObject("error", "Error:  %s".formatted(e.getReason()));
            mvc.setStatus(HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        mvc.setViewName("summary");
        return mvc;

        // post .../login
        // if fail, send back to login page
        // if success, send to logging page

        // loger page
        // Get to give logger form to them
        // POST to receive the form
        // POST to send to database

        // result page
        // GET from DB & API (additional info)
        // can put query string here, maybe can have a form asking for dates to
        // return or ask for muscle group/ filter.
    }
}
