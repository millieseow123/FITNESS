package vttp2022.mini.project.Fitness.models;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.util.MultiValueMap;

public class Utilities {

    public static User convertSignUp(MultiValueMap<String, String> form) {
        User user = new User();
        user.setEmail(form.getFirst("email"));
        user.setPassword(form.getFirst("password"));
        user.setFull_name(form.getFirst("full_name"));

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(form.getFirst("dob"));
            user.setDob(date);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public static User convertLogin(MultiValueMap<String, String> form) {
        User user = new User();
        user.setEmail(form.getFirst("email"));
                user.setPassword(form.getFirst("password"));

        return user;
    }

    public static User convertLogin(SqlRowSet rs) {
        User user = new User();
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));

        return user;
    }

    public static Tracker convertExercise(MultiValueMap<String, String> form) {
        Tracker tracker = new Tracker();
        tracker.setType_of_exercise(form.getFirst("type_of_exercise"));
        tracker.setDuration(form.getFirst("duration"));
        tracker.setCalories(Integer.parseInt(form.getFirst("calories")));
        tracker.setMuscle_group(form.getFirst("muscle_group"));
        tracker.setEmail(form.getFirst("email"));

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(form.getFirst("date"));
            tracker.setDate(date);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return tracker;
    }
}
