package palindrome.game.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import palindrome.game.service.PalindromeService;

public class PalindromeServiceTest {

	@Test
	public void testPalindrome() {

		PalindromeService checker = new PalindromeService(false, false);

		assertNotPalindrome(checker, "A but tuba.");
		assertNotPalindrome(checker, "A new order began, a more Roman age bred Rowena.");
		assertNotPalindrome(checker, "A nut for a jar of tuna.");
		assertNotPalindrome(checker, "A Santa at Nasa.");
		assertNotPalindrome(checker, "A Santa dog lived as a devil God at NASA.");
		assertNotPalindrome(checker, "A tin mug for a jar of gum, Nita.");
		assertNotPalindrome(checker, "A Toyota’s a Toyota.");
		assertNotPalindrome(checker, "Able was I ere I saw Elba.");
		assertNotPalindrome(checker, "Acrobats stab orca.");
		assertNotPalindrome(checker, "Ah, Satan sees Natasha!");

		assertPalindrome(checker, "a but tuba");
		assertPalindrome(checker, "a new order began a more roman age bred rowena");
		assertPalindrome(checker, "a nut for a jar of tuna");
		assertPalindrome(checker, "a santa at nasa");
		assertPalindrome(checker, "a santa dog lived as a devil god at nasa");
		assertPalindrome(checker, "a tin mug for a jar of gum nita");
		assertPalindrome(checker, "a toyotas a toyota");
		assertPalindrome(checker, "able was i ere i saw elba");
		assertPalindrome(checker, "acrobats stab orca");
		assertPalindrome(checker, "ah satan sees natasha");
	}

	@Test
	public void testPalindromeIgnorecase() {

		PalindromeService checker = new PalindromeService(true, false);

		assertPalindrome(checker, "A BUT tuba");
		assertPalindrome(checker, "a but tuba");

		assertPalindrome(checker, "A nEw oRder BEGAN A MORE ROMAN AGE BRED ROWENA");
		assertPalindrome(checker, "a new order began a more roman age bred rowena");
	}

	@Test
	public void testPalindromeIgnorePunctuation() {

		PalindromeService checker = new PalindromeService(false, true);

		assertPalindrome(checker, "a but tuba.");
		assertPalindrome(checker, "a new order began, a more roman age bred rowena.");
		assertPalindrome(checker, "a nut for a jar of tuna.");
		assertPalindrome(checker, "a santa at nasa.");
		assertPalindrome(checker, "a santa dog lived as a devil god at nasa.");
		assertPalindrome(checker, "a tin mug for a jar of gum, nita.");
		assertPalindrome(checker, "a toyota's a toyota.");
		assertPalindrome(checker, "able was i ere i saw elba.");
		assertPalindrome(checker, "acrobats stab orca.");
		assertPalindrome(checker, "ah, satan sees natasha!");

		assertNotPalindrome(checker, "A but tuba.");
		assertNotPalindrome(checker, "A new order began, a more Roman age bred Rowena.");
		assertNotPalindrome(checker, "A nut for a jar of tuna.");
		assertNotPalindrome(checker, "A Santa at Nasa.");
		assertNotPalindrome(checker, "A Santa dog lived as a devil God at NASA.");
		assertNotPalindrome(checker, "A tin mug for a jar of gum, Nita.");
		assertNotPalindrome(checker, "A Toyota’s a Toyota.");
		assertNotPalindrome(checker, "Able was I ere I saw Elba.");
		assertNotPalindrome(checker, "Acrobats stab orca.");
		assertNotPalindrome(checker, "Ah, Satan sees Natasha!");

	}

	private static void assertPalindrome(PalindromeService checker, String input) {
		assertTrue(checker.isPalindrome(input));
	}

	private static void assertNotPalindrome(PalindromeService checker, String input) {
		assertFalse(checker.isPalindrome(input));
	}
}
