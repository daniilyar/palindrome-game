package palindrome.game.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Set;

import org.junit.Test;

import palindrome.game.GameException;
import palindrome.game.domain.User;

public class PalindromeServiceTest {

	@Test
	public void testCheckPalindrome() {

		PalindromeService service = new PalindromeService(false, false);

		assertNotPalindrome(service, "A but tuba.");
		assertNotPalindrome(service, "A new order began, a more Roman age bred Rowena.");
		assertNotPalindrome(service, "A nut for a jar of tuna.");
		assertNotPalindrome(service, "A Santa at Nasa.");
		assertNotPalindrome(service, "A Santa dog lived as a devil God at NASA.");
		assertNotPalindrome(service, "A tin mug for a jar of gum, Nita.");
		assertNotPalindrome(service, "A Toyota’s a Toyota.");
		assertNotPalindrome(service, "Able was I ere I saw Elba.");
		assertNotPalindrome(service, "Acrobats stab orca.");
		assertNotPalindrome(service, "Ah, Satan sees Natasha!");

		assertPalindrome(service, "a but tuba");
		assertPalindrome(service, "a new order began a more roman age bred rowena");
		assertPalindrome(service, "a nut for a jar of tuna");
		assertPalindrome(service, "a santa at nasa");
		assertPalindrome(service, "a santa dog lived as a devil god at nasa");
		assertPalindrome(service, "a tin mug for a jar of gum nita");
		assertPalindrome(service, "a toyotas a toyota");
		assertPalindrome(service, "able was i ere i saw elba");
		assertPalindrome(service, "acrobats stab orca");
		assertPalindrome(service, "ah satan sees natasha");
	}

	@Test
	public void testCheckPalindromeIgnorecase() {

		PalindromeService service = new PalindromeService(true, false);

		assertPalindrome(service, "A BUT tuba");
		assertPalindrome(service, "a but tuba");

		assertPalindrome(service, "A nEw oRder BEGAN A MORE ROMAN AGE BRED ROWENA");
		assertPalindrome(service, "a new order began a more roman age bred rowena");
	}

	@Test
	public void testCheckPalindromeIgnorePunctuation() {

		PalindromeService service = new PalindromeService(false, true);

		assertPalindrome(service, "a but tuba.");
		assertPalindrome(service, "a new order began, a more roman age bred rowena.");
		assertPalindrome(service, "a nut for a jar of tuna.");
		assertPalindrome(service, "a santa at nasa.");
		assertPalindrome(service, "a santa dog lived as a devil god at nasa.");
		assertPalindrome(service, "a tin mug for a jar of gum, nita.");
		assertPalindrome(service, "a toyota's a toyota.");
		assertPalindrome(service, "able was i ere i saw elba.");
		assertPalindrome(service, "acrobats stab orca.");
		assertPalindrome(service, "ah, satan sees natasha!");

		assertNotPalindrome(service, "A but tuba.");
		assertNotPalindrome(service, "A new order began, a more Roman age bred Rowena.");
		assertNotPalindrome(service, "A nut for a jar of tuna.");
		assertNotPalindrome(service, "A Santa at Nasa.");
		assertNotPalindrome(service, "A Santa dog lived as a devil God at NASA.");
		assertNotPalindrome(service, "A tin mug for a jar of gum, Nita.");
		assertNotPalindrome(service, "A Toyota’s a Toyota.");
		assertNotPalindrome(service, "Able was I ere I saw Elba.");
		assertNotPalindrome(service, "Acrobats stab orca.");
		assertNotPalindrome(service, "Ah, Satan sees Natasha!");

	}

	@Test
	public void testSubmitPalindrome() throws GameException {

		PalindromeService service = new PalindromeService(true, true);
		User user = new User("Ben", "Franklin");
		String palindrome = "Ah, Satan sees Natasha!";
		service.submitPalindrome(user, palindrome);

		Set<String> submittedPalindromes = service.getSubmittedPalindromes(user);
		assertEquals(1, submittedPalindromes.size());

		assertEquals(palindrome, submittedPalindromes.iterator().next());

		// Try to submit the same palindrome again
		try {
			service.submitPalindrome(user, palindrome);
			fail();
		} catch (GameException e) {
			assertTrue(e.getMessage().contains(palindrome));
			assertTrue(e.getMessage().contains("has been already submitted before"));
		}

		// Try to submit empty palindrome
		try {
			service.submitPalindrome(user, "");
			fail();
		} catch (GameException e) {
			assertTrue(e.getMessage().contains("Empty string could not be a palindrome"));
		}

		// Try to submit null palindrome
		try {
			service.submitPalindrome(user, null);
			fail();
		} catch (GameException e) {
			assertTrue(e.getMessage().contains("Empty string could not be a palindrome"));
		}
	}

	private static void assertPalindrome(PalindromeService service, String input) {
		assertTrue(service.isPalindrome(input));
	}

	private static void assertNotPalindrome(PalindromeService service, String input) {
		assertFalse(service.isPalindrome(input));
	}
}
