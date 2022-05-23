package vttp2022.mini.project.Fitness.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import vttp2022.mini.project.Fitness.models.Tracker;
import vttp2022.mini.project.Fitness.repositories.TrackerRepository;

@Service
public class TrackerService {

    @Autowired
    private TrackerRepository trackerRepo;

    public void addNewExercise(Tracker tracker) throws FitnessException {
        if (!trackerRepo.addNewExercise(tracker))
            throw new FitnessException("Error adding exercise. Please check with Admin.");
    }

    public List<Tracker> getAllExercises() {
        return trackerRepo.selectAllExercises();
    }

    public static final String wgerAPI = "https://wger.de/api/v2/";
    // @Value("${wger.api.key}")
    private String exerciseinfo;

    public List<String> getWorkouts() { 
        List<String> result = new LinkedList<>();

        String url = UriComponentsBuilder.fromUriString(wgerAPI)
                .queryParam("exerciseinfo", exerciseinfo)
                .toUriString();

        RequestEntity<Void> req = RequestEntity.get(url).build();

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> resp = null;

        try {
            resp = template.exchange(req, String.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }            
        return result;

    }

}
