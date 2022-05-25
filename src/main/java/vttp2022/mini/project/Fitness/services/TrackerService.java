package vttp2022.mini.project.Fitness.services;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
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

    public static final String baseURL = "https://wger.de/api/v2%s";
    public static final String exercise = "/exercise/";
    public static final String muscle = "/muscle/";
    public static final String nutrition = "/ingredient/";


    // @Value("${wger.api.key}")

    public List<String> getWorkouts(Integer limit) {

        List<String> result = new LinkedList<>();
        String url = UriComponentsBuilder.fromUriString(baseURL.formatted(exercise))
                .queryParam("language", 2)
                .queryParam("limit", limit)
                .toUriString();
        RequestEntity<Void> req = RequestEntity.get(url).build();

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> resp = null;

        try {
            resp = template.exchange(req, String.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return result;
        }
        JsonReader reader = Json.createReader(new StringReader(resp.getBody()));
        JsonObject workouts = reader.readObject();
        jakarta.json.JsonArray results = workouts.getJsonArray("results");

        JsonObject object = null;
        for (int i = 0; i < results.size(); i++) {
            object = results.getJsonObject(i);
            result.add(object.getString("name"));
        }

        return result;
    }

    public List<String> getMuscleName(String name) {

        List<String> result = new LinkedList<>();
        String url = UriComponentsBuilder.fromUriString(baseURL.formatted(muscle))
                .queryParam("ordering", name)
                .toUriString();
        RequestEntity<Void> req = RequestEntity.get(url).build();

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> resp = null;

        try {
            resp = template.exchange(req, String.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return result;
        }
        JsonReader reader = Json.createReader(new StringReader(resp.getBody()));
        JsonObject muscle = reader.readObject();
        jakarta.json.JsonArray results = muscle.getJsonArray("results");
        JsonObject object = null;
        for (int i = 0; i < results.size(); i++) {
            object = results.getJsonObject(i);
            result.add(object.getString("name"));
            // result.add(object.getString("name_en"));

        }
        return result;

    }

    public List<String> getMusclePosition(String position) {

        List<String> result = new LinkedList<>();
        String url = UriComponentsBuilder.fromUriString(baseURL.formatted(muscle))
                .queryParam("is_front", position)
                .toUriString();
        RequestEntity<Void> req = RequestEntity.get(url).build();

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> resp = null;

        try {
            resp = template.exchange(req, String.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return result;
        }
        JsonReader reader = Json.createReader(new StringReader(resp.getBody()));
        JsonObject muscle = reader.readObject();
        jakarta.json.JsonArray results = muscle.getJsonArray("results");
        JsonObject object = null;
        for (int i = 0; i < results.size(); i++) {
            object = results.getJsonObject(i);
            result.add(object.getString("name"));
            // result.add(object.getString("name_en"));

        }
        return result;
    }

    public List<String> getNutrition(Integer limit) {

        List<String> result = new LinkedList<>();
        String url = UriComponentsBuilder.fromUriString(baseURL.formatted(nutrition))
                .queryParam("language", 2)
                .queryParam("limit", limit)
                .toUriString();
        RequestEntity<Void> req = RequestEntity.get(url).build();

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> resp = null;

        try {
            resp = template.exchange(req, String.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return result;
        }
        JsonReader reader = Json.createReader(new StringReader(resp.getBody()));
        JsonObject workouts = reader.readObject();
        jakarta.json.JsonArray results = workouts.getJsonArray("results");

        JsonObject object = null;
        for (int i = 0; i < results.size(); i++) {
            object = results.getJsonObject(i);
            result.add(object.getString("name"));
            //energy
            //protein
            //carbs
            //fat
            //fibres
            //sodium
        }

        return result;
    }
}
