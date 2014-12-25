package palindrome.game.service;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import palindrome.game.domain.User;
import palindrome.game.domain.UserPoints;

public class UserService implements IUserService {

	private final Set<UserPoints> userPointsSet = new TreeSet<>();

	@Override
	public UserPoints addNewUser(User user) {

		UserPoints userPoints = getUserPoints(user);
		if (userPoints == null) {
			userPoints = new UserPoints(user, 0);
		} else {
			String errorMsg = String.format("User %s already exists and has %s points", user, userPoints.getPoints());
			throw new IllegalStateException(errorMsg);
		}

		userPointsSet.add(userPoints);

		return userPoints;
	}

	@Override
	public void addPoints(User user, int points) {
		UserPoints userPoints = getUserPoints(user);
		userPointsSet.remove(userPoints);
		userPointsSet.add(userPoints.addPoints(points));
	}

	@Override
	public int getPoints(User user) {
		return getUserPoints(user).getPoints();
	}

	@Override
	public void setPoints(User user, int points) {
		UserPoints userPoints = getUserPoints(user);
		userPointsSet.remove(userPoints);
		userPointsSet.add(userPoints.setPoints(points));
	}

	@Override
	public Set<UserPoints> getTopUsers(int limit) {

		userPointsSet.toArray();

		int count = 0;

		Set<UserPoints> result = new HashSet<>();

		for (UserPoints userPoints : userPointsSet) {

			result.add(userPoints);

			count++;

			if (count >= limit) {
				break;
			}
		}

		return result;
	}

	private UserPoints getUserPoints(User user) {

		UserPoints result = null;

		for (UserPoints userPoints : userPointsSet) {
			if (userPoints.getUser().equals(user)) {
				result = userPoints;
				break;
			}
		}

		return result;
	}
}
