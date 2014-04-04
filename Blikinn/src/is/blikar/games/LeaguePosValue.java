package is.blikar.games;

/**
 * 
 * @author Aðalsteinn Stefánsson
 * @since 22.11.13
 * Description: Setters and getters for League Table
 *
 */
public class LeaguePosValue {
	String	title, position, team, wins, draws,
			losses, goals_for, goals_against,
			points;
	int 	games;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getWins() {
		return wins;
	}

	public void setWins(String wins) {
		this.wins = wins;
	}

	public String getDraws() {
		return draws;
	}

	public void setDraws(String draws) {
		this.draws = draws;
	}

	public String getLosses() {
		return losses;
	}

	public void setLosses(String losses) {
		this.losses = losses;
	}
	
	public int getGames() {
		return games;
	}

	public void setGames(String games) {
		int winsInt = Integer.parseInt(getWins());
		int drawsInt = Integer.parseInt(getDraws());
		int lossesInt = Integer.parseInt(getLosses());
		int totalGames = winsInt + drawsInt + lossesInt;
		this.games = totalGames;
	}	
	
	public String getGoalsFor() {
		return goals_for;
	}

	public void setGoalsFor(String goals_for) {
		this.goals_for = goals_for;
	}
	
	public String getGoalsAgainst() {
		return goals_against;
	}

	public void setGoalsAgainst(String goals_against) {
		this.goals_against = goals_against;
	}
	
	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}
}