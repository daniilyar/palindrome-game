package palindrome.game.service;

public class PalindromeService {

	private static final String REGEXP_SPACE_SYMBOLS = "\\s+";

	private static final String REGEXP_PUNCTUATION_SYMBOLS = "\\p{Punct}";

	private final boolean ignoreCase;
	private final boolean ignorePunctuation;

	public PalindromeService(boolean ignoreCase, boolean ignorePunctuation) {
		this.ignoreCase = ignoreCase;
		this.ignorePunctuation = ignorePunctuation;
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

	public int countBonuses(String palindrome) {
		return palindrome.length();
	}

}
