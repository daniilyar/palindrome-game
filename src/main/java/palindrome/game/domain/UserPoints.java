package palindrome.game.domain;

public class UserPoints implements Comparable<UserPoints> {

	private final User user;
	private final int points;

	public UserPoints(User user) {
		this.user = user;
		points = 0;
	}

	public UserPoints(User user, int points) {
		this.user = user;
		this.points = points;
	}

	public UserPoints addPoints(int pointsToAdd) {
		return new UserPoints(user, points + pointsToAdd);
	}

	public UserPoints setPoints(int points) {
		return new UserPoints(user, points);
	}

	public User getUser() {
		return user;
	}

	public int getPoints() {
		return points;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + points;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		UserPoints other = (UserPoints) obj;
		if (points != other.points) {
			return false;
		}
		if (user == null) {
			if (other.user != null) {
				return false;
			}
		} else if (!user.equals(other.user)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "UserPoints [user=" + user + ", points=" + points + "]";
	}

	@Override
	public int compareTo(UserPoints compareWith) {
		Integer points = compareWith.getPoints();
		return points.compareTo(this.points);
	}

}
