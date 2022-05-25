package vttp2022.mini.project.Fitness.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import vttp2022.mini.project.Fitness.models.Tracker;
import vttp2022.mini.project.Fitness.services.FitnessException;
import vttp2022.mini.project.Fitness.services.UserService;
import vttp2022.mini.project.Fitness.services.TrackerService;

import static vttp2022.mini.project.Fitness.models.Utilities.*;

import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
public class FitnessController {


    @Autowired
    private TrackerService trackerSvc;
  

    @PostMapping("/log") 
    public ModelAndView logExercise(@RequestBody MultiValueMap<String, String> form, HttpSession sess ) {
        String username = (String)sess.getAttribute("username");
        form.add("email", username);
        Tracker tracker = convertExercise(form);
   
        ModelAndView mvc = new ModelAndView();
        try {
            trackerSvc.addNewExercise(tracker);
            mvc.addObject("logSuccess",
                    "Sucessfully added exercise");
        } catch (FitnessException e) {
            mvc.setViewName("error");
            mvc.addObject("logError", "Error:  %s".formatted(e.getReason()));
            mvc.setStatus(HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }
        mvc.setViewName("logSuccess");
        
        return mvc;
    }

    @GetMapping("/summary")
    public ModelAndView getAllExercises() {
        ModelAndView mvc = new ModelAndView();

        mvc.setViewName("summary");
        List<Tracker> tracker = trackerSvc.getAllExercises();
        mvc.addObject("exercises", tracker);

        return mvc;
    }

    @GetMapping("/exercisecategory")
    public ModelAndView getSearch(Integer limit) {

        ModelAndView mvc = new ModelAndView();
        List<String> workouts = trackerSvc.getWorkouts(limit);
        String HTMLstring = workouts.toString().replaceAll("[\\[\\]]", "");
        //Document html = Jsoup.parse(HTMLstring);     
        
        mvc.addObject("html", HTMLstring);
        mvc.setViewName("findWorkoutResults");

        return mvc;
    }

    @GetMapping("/muscleName")
    public ModelAndView getMuscleName(String name) {

        ModelAndView mvc = new ModelAndView();
        List<String> muscle = trackerSvc.getMuscleName(name);
        String HTMLstring = muscle.toString().replaceAll("[\\[\\]]", "");
        mvc.addObject("muscle", HTMLstring);
        mvc.setViewName("musclegroupsResults");

        return mvc;
    }

    @GetMapping("/muscleFront")
    public ModelAndView getMusclePosition(@RequestParam String position) {

        ModelAndView mvc = new ModelAndView();
        List<String> muscle = trackerSvc.getMusclePosition(position);
        String HTMLstring = muscle.toString().replaceAll("[\\[\\]]", "");
        mvc.addObject("muscle", HTMLstring);
        mvc.setViewName("musclegroupsResults");

        return mvc;
    }

    @GetMapping("/nutrition")
    public ModelAndView getNutrition(Integer limit) {

        ModelAndView mvc = new ModelAndView();
        List<String> nutrition = trackerSvc.getNutrition(limit);
        String HTMLstring = nutrition.toString().replaceAll("[\\[\\]]", "");
        //Document html = Jsoup.parse(HTMLstring);     
        
        mvc.addObject("html", HTMLstring);
        mvc.setViewName("nutritionResults");

        return mvc;
    }

    @PostMapping("/bmi")
    public ModelAndView bmi(@RequestParam float height, @RequestParam float weight) {

        float BMI = weight/(height * height);

        ModelAndView mvc = new ModelAndView();
            mvc.setViewName("bodyweightResults");
            mvc.addObject("BMI", BMI);
        
        return mvc;
    }   

}
