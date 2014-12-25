package palindrome.game;

public class GameException extends Exception {

	private static final long serialVersionUID = 1L;

	public GameException(String message) {
		super(message);
	}

	public GameException(String message, Throwable e) {
		super(message, e);
	}
}
