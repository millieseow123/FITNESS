package vttp2022.mini.project.Fitness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vttp2022.mini.project.Fitness.controllers.FitnessController;
import vttp2022.mini.project.Fitness.models.User;
import vttp2022.mini.project.Fitness.repositories.UserRepository;
import vttp2022.mini.project.Fitness.services.FitnessException;
import vttp2022.mini.project.Fitness.services.TrackerService;
import vttp2022.mini.project.Fitness.services.UserService;

@SpringBootTest
class FitnessApplicationTests {

	@Autowired
	private TrackerService trackerSvc;

	@Autowired
	private UserService userSvc;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private FitnessController fController;

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

	private User fred;

	public FitnessApplicationTests() {
		fred = new User();
		fred.setFull_name("fred");
		fred.setEmail("fred@gmail.com");
		fred.setPassword("fredfred123");

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.YEAR, 2022);
		fred.setDob(cal.getTime());
	}

	@BeforeEach
	public void setup() throws FitnessException {
		userSvc.addNewUser(fred);
	}

	@AfterEach
	public void tearDown() {
		userRepo.deleteUserByEmail(fred.getEmail());
	}

	@Test
	void insertFredShouldFail() {
		try {
			userSvc.addNewUser(fred);
		} catch (FitnessException e) {
			assertTrue(true);
			return;
		}
		fail("Did not throw FitnessException when email exists");
	}

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

	//test controller
	@Test
	void contextLoads() {
		assertNotNull(fController);
	}

}
