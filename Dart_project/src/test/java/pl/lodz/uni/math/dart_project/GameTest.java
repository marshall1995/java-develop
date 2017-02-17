package pl.lodz.uni.math.dart_project;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class GameTest {

	Game game;

	@Before
	public void setGame() {
		game = new Game();
	}

	@Test
	public void winDartGameTest() {
		game.newGame();
		game.throwDart(Points.TWENTY, Multipliers.TRIPLE);
		game.throwDart(Points.TWENTY, Multipliers.TRIPLE);
		game.throwDart(Points.TWENTY, Multipliers.TRIPLE);
		game.throwDart(Points.TWENTY, Multipliers.TRIPLE);
		game.throwDart(Points.TWENTY, Multipliers.TRIPLE);
		game.throwDart(Points.TWENTY, Multipliers.TRIPLE);
		game.throwDart(Points.ONE, Multipliers.SINGLE);
		game.throwDart(Points.TWENTY, Multipliers.SINGLE);
		game.throwDart(Points.TWENTY, Multipliers.DOUBLE);
		assertEquals(0, game.getScore());
	}

	@Test
	public void unlimitedDartGameHitingToOneTest() {
		game.newGame();
		game.throwDart(Points.TWENTY, Multipliers.TRIPLE);
		game.throwDart(Points.TWENTY, Multipliers.TRIPLE);
		game.throwDart(Points.TWENTY, Multipliers.TRIPLE);
		game.throwDart(Points.TWENTY, Multipliers.TRIPLE);
		game.throwDart(Points.TWENTY, Multipliers.TRIPLE);
		game.throwDart(Points.TWENTY, Multipliers.TRIPLE);
		game.throwDart(Points.TWENTY, Multipliers.TRIPLE);
		game.throwDart(Points.TWENTY, Multipliers.TRIPLE);
		assertEquals(61, game.getScore());
	}

	@Test
	public void unlimitedDartGameHitingLowerThenZeroTest() {
		game.newGame();
		game.throwDart(Points.TWENTY, Multipliers.TRIPLE);
		game.throwDart(Points.TWENTY, Multipliers.TRIPLE);
		game.throwDart(Points.TWENTY, Multipliers.TRIPLE);
		game.throwDart(Points.TWENTY, Multipliers.TRIPLE);
		game.throwDart(Points.TWENTY, Multipliers.SINGLE);
		game.throwDart(Points.ONE, Multipliers.SINGLE);
		game.throwDart(Points.TWENTY, Multipliers.TRIPLE);
		assertEquals(40, game.getScore());
	}

	@Test
	public void unlimitedDartGameLastScoreWasNotDouble() {
		game.newGame();
		game.throwDart(Points.TWENTY, Multipliers.TRIPLE);
		game.throwDart(Points.TWENTY, Multipliers.TRIPLE);
		game.throwDart(Points.TWENTY, Multipliers.TRIPLE);
		game.throwDart(Points.TWENTY, Multipliers.TRIPLE);
		game.throwDart(Points.TWENTY, Multipliers.SINGLE);
		game.throwDart(Points.ONE, Multipliers.SINGLE);
		game.throwDart(Points.TEN, Multipliers.SINGLE);
		game.throwDart(Points.TEN, Multipliers.TRIPLE);
		assertEquals(30, game.getScore());
	}
}