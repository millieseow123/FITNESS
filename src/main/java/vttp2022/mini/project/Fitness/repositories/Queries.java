package vttp2022.mini.project.Fitness.repositories;

public interface Queries {
    public static final String SQL_SELECT_USER_BY_EMAIL = 
        "select * from user where email = ?";

    public static final String SQL_INSERT_NEW_USER = 
        "insert into user (email, password, full_name, dob) values (?, sha1(?), ?, ?)";

    public static final String SQL_SELECT_AND_COUNT_USERS_FROM_EMAIL = 
        "select count(*) as user_count from user where email = ? and password = sha1(?)";

    public static final String SQL_INSERT_NEW_EXERCISE = 
        "insert into tracker (date, type_of_exercise, duration, calories, muscle_group, email) values (?, ?, ?, ?, ?, ?)";

    public static final String SQL_SELECT_ALL_FROM_TRACKER =
        "select * from tracker";
}
