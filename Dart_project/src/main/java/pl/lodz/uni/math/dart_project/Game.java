package pl.lodz.uni.math.dart_project;

import org.apache.log4j.Logger;

public class Game {

	private static Logger logger = Logger.getLogger(Game.class.getName());

	private int score;

	public void throwDart(Points pointHit, Multipliers multiplier) {
		if (score == 0) {
			logger.info("Start new game?");
		} else {
			tryChangeScore(pointHit, multiplier);
			validScore(pointHit, multiplier);
			logger.info("Score: " + getScore());
		}
	}

	private void validScore(Points pointHit, Multipliers multiplier) {
		if (score == 0) {
			checkIfLastHitWasDouble(pointHit, multiplier);
		} else if (score < 0 || score == 1) {
			doWhenScoreLowerThenZeroOrIsOne(pointHit, multiplier);
		}
	}

	private void checkIfLastHitWasDouble(Points pointHit, Multipliers multiplier) {
		if (!multiplier.equals(Multipliers.DOUBLE)) {
			int pointsScored = pointHit.getValue() * multiplier.getValue();
			setScore(score + pointsScored);
			logger.info("Last hit was not double");
		} else {
			logger.info("You won!");
		}
	}

	private void doWhenScoreLowerThenZeroOrIsOne(Points pointHit, Multipliers multiplier) {
		int pointsScored = pointHit.getValue() * multiplier.getValue();
		setScore(score + pointsScored);
		logger.info("You scored too many points!");
	}

	private void checkMultiplier(Points pointHit, Multipliers multiplier) {
		if (!multiplier.equals(Multipliers.SINGLE)) {
			if (pointHit.equals(Points.BULL) || pointHit.equals(Points.BULLSEYE)) {
				multiplier = Multipliers.SINGLE;
			}
		}
	}

	private void tryChangeScore(Points pointHit, Multipliers multiplier) {
		checkMultiplier(pointHit, multiplier);
		int pointsScored = pointHit.getValue() * multiplier.getValue();
		setScore(score - pointsScored);
	}

	public void newGame() {
		setScore(301);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
