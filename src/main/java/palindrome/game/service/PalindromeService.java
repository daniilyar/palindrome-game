package palindrome.game.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import palindrome.game.GameException;
import palindrome.game.domain.User;

public class PalindromeService {

	private static final String REGEXP_SPACE_SYMBOLS = "\\s+";

	private static final String REGEXP_PUNCTUATION_SYMBOLS = "\\p{Punct}";

	private final boolean ignoreCase;
	private final boolean ignorePunctuation;

	private Map<User, Set<String>> submittedPalindromesMap = new HashMap<>();

	public PalindromeService(boolean ignoreCase, boolean ignorePunctuation) {
		this.ignoreCase = ignoreCase;
		this.ignorePunctuation = ignorePunctuation;
	}

	public int submitPalindrome(User user, String palindrome) throws GameException {

		if (palindrome == null || palindrome.isEmpty()) {
			throw new GameException("Empty string could not be a palindrome");
		}

		Set<String> submittedPalindromes = submittedPalindromesMap.get(user);
		if (submittedPalindromes == null) {
			submittedPalindromes = new HashSet<>();
		}

		int points;

		if (submittedPalindromes.add(palindrome)) {
			points = palindrome.length();
			submittedPalindromesMap.put(user, submittedPalindromes);
		} else {
			throw new GameException("Palindrome '" + palindrome + "' has been already submitted before");
		}

		return points;
	}

	public Set<String> getSubmittedPalindromes(User user) {
		return submittedPalindromesMap.get(user);
	}

	public boolean isPalindrome(String input) {

		boolean result = true;

		String text = input.replaceAll(REGEXP_SPACE_SYMBOLS, "");

		if (ignoreCase) {
			text = text.toLowerCase();
		}

		if (ignorePunctuation) {
			text = text.replaceAll(REGEXP_PUNCTUATION_SYMBOLS, "");
		}

		int textLength = text.length();

		for (int i = 0; i < textLength / 2; i++) {
			if (text.charAt(textLength - i - 1) != text.charAt(i)) {
				result = false;
				break;
			}
		}

		return result;
	}

}
