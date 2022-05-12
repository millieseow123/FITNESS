package vttp2022.mini.project.Fitness.repositories;
import vttp2022.mini.project.Fitness.models.Tracker;
import vttp2022.mini.project.Fitness.models.User;
import static vttp2022.mini.project.Fitness.repositories.Queries.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class TrackerRepository {

    @Autowired
    private JdbcTemplate template;
    
    public boolean logExercise(Tracker tracker){
        int count = template.update(SQL_INSERT_NEW_EXERCISE, tracker.getDate(), tracker.getType_of_exercise(), tracker.getDuration(), tracker.getCalories(), tracker.getMuscle_group());
        return 1 == count;
    }}
    
