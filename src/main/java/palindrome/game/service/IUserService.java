package palindrome.game.service;

import java.util.Set;

import palindrome.game.domain.User;
import palindrome.game.domain.UserPoints;

public interface IUserService {

	UserPoints addNewUser(User user);

	void addPoints(User user, int points);

	int getPoints(User user);

	void setPoints(User user, int points);

	Set<UserPoints> getTopUsers(int limit);
}