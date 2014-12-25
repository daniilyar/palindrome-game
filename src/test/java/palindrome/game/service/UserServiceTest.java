package palindrome.game.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import palindrome.game.domain.User;
import palindrome.game.domain.UserPoints;

public class UserServiceTest {

	private UserService userService;

	@Before
	public void before() {
		userService = new UserService();
	}

	@Test
	public void testAddPoints() {

		User user1 = new User("Ben", "Franklin");
		userService.addNewUser(user1);
		userService.addPoints(user1, 5);

		assertEquals(userService.getPoints(user1), 5);
	}

	@Test
	public void testSetPoints() {

		User user = new User("Ben", "Franklin");
		userService.addNewUser(user);
		userService.setPoints(user, 5);

		assertEquals(userService.getPoints(user), 5);
	}

	@Test
	public void testAddExistingUser() {

		User user = new User("Ben", "Franklin");
		userService.addNewUser(user);

		try {
			userService.addNewUser(user);
			fail();
		} catch (Exception e) {
			assertTrue(e.getMessage().contains(user.getFirstName()));
		}
	}

	@Test
	public void testGetTopNUsers() {

		User user1 = new User("Ben", "Franklin");
		userService.addNewUser(user1);
		userService.setPoints(user1, 5);

		User user2 = new User("Loisa", "Buttlewort");
		userService.addNewUser(user2);
		userService.setPoints(user2, 3);

		User user3 = new User("Leonardo", "Da-Vinci");
		userService.addNewUser(user3);
		userService.setPoints(user3, 4);

		User user4 = new User("Muslim", "Magomaev"); // 1st
		userService.addNewUser(user4);
		userService.setPoints(user4, 50);

		User user5 = new User("Jouge", "Washington");
		userService.addNewUser(user5);
		userService.setPoints(user5, 10);

		User user6 = new User("Bill", "Clinton"); //second
		userService.addNewUser(user6);
		userService.setPoints(user6, 11);

		UserPoints[] topUsers = userService.getTopUsers(2).toArray(new UserPoints[] {});

		assertEquals(topUsers[0].getUser(), user4);
		assertEquals(topUsers[1].getUser(), user6);

	}

}
