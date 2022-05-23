package vttp2022.mini.project.Fitness.repositories;

import vttp2022.mini.project.Fitness.models.Tracker;
import vttp2022.mini.project.Fitness.models.User;
import static vttp2022.mini.project.Fitness.repositories.Queries.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import static vttp2022.mini.project.Fitness.models.Utilities.*;

@Repository
public class TrackerRepository {

    @Autowired
    private JdbcTemplate template;

    public boolean addNewExercise(Tracker tracker) {
        int count = template.update(SQL_INSERT_NEW_EXERCISE, tracker.getDate(), tracker.getType_of_exercise(),
                tracker.getDuration(), tracker.getCalories(), tracker.getMuscle_group(), tracker.getEmail());
        return 1 == count;
    }

    public List<Tracker> selectAllExercises() {
        List<Tracker> exercises = new LinkedList<>();
        final SqlRowSet rs = template.queryForRowSet(SQL_SELECT_ALL_FROM_TRACKER);
        while (rs.next()) {
            Tracker tracker = convertExercise(rs);
            exercises.add(tracker);
        }
        return exercises;
    }
}
