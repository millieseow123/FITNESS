package vttp2022.mini.project.Fitness.models;

import java.util.Date;

public class Tracker {
    private Date date;
    private String type_of_exercise;
    private String duration;
    private int calories;
    private String muscle_group;
    
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getType_of_exercise() {
        return type_of_exercise;
    }
    public void setType_of_exercise(String type_of_exercise) {
        this.type_of_exercise = type_of_exercise;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public int getCalories() {
        return calories;
    }
    public void setCalories(int calories) {
        this.calories = calories;
    }
    public String getMuscle_group() {
        return muscle_group;
    }
    public void setMuscle_group(String muscle_group) {
        this.muscle_group = muscle_group;
    }

    
}
