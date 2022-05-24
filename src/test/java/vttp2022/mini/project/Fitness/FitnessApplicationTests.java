package vttp2022.mini.project.Fitness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vttp2022.mini.project.Fitness.services.TrackerService;
import vttp2022.mini.project.Fitness.services.UserService;

@SpringBootTest
class FitnessApplicationTests {

	@Autowired
	private TrackerService trackerSvc;

	@Autowired
	private UserService userSvc;

	//test service
	@Test
	void shouldGet5Workouts() {
		List<String> workouts = trackerSvc.getWorkouts(5);
		assertEquals(5, workouts.size(), "workouts=5");
	}
	@Test
	void shouldGet17NutritionalData() {
		List<String> nutrition = trackerSvc.getNutrition(17);
		assertEquals(17, nutrition.size(), "nutrition = 17");
	}

	//test authenticate
	@Test
	void signinShouldFail() {
		try {
			userSvc.authenticate("test@gmail.com", "sha1");
		} catch (Exception e) {
			assertTrue(true);
			return;
		}
		fail("invalid");
	}


}
