package tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
//@author Xiyin Liang, Kateryna Semenova

import code.Board;
import code.Game;
import code.Location;

public class WinningStateTest {
	@Test
	public void checkForAssassinTestNoAssassin() {
		Game game = new Game();
		Board board = game.getBoard();
		Location[][] arr = board.getLocations();

		boolean assassinExists = false;
		for (int i = 0; i < 5; i++) {
			for (int n = 0; n < 5; n++) {
				Location loc = arr[i][n];
				if (loc.isVisible() && loc.getPerson().getAgentType() == 2) {// if location is visible and it's an
																				// assassin.
					assassinExists = true;

				}
			}
		}

		assertEquals("checkForAssassin should return false as no assassin exists", assassinExists,
				board.checkForAssassin());
	}

	@Test
	public void checkForAssassinTestWithAssassin() {
		Game game = new Game();
		Board board = game.getBoard();
		Location[][] arr = board.getLocations();

		for (int i = 0; i < 5; i++) {
			for (int n = 0; n < 5; n++) {
				Location loc = arr[i][n];
				if (loc.getPerson().getAgentType() == 2) {// if location is visible and it's an assassin.
					loc.setVisible(true);
				}
			}
		}

		assertEquals("checkForAssassin should return false as no assassin exists", true, board.checkForAssassin());
	}

	@Test
	public void allAgentsRevealedTestNoWinner() {
		Game game = new Game();
		Board board = game.getBoard();

		assertEquals("There should be no winner since no agents are revealed", false, board.allAgentsRevealed());
		assertEquals("There should be no winner declared", "", game.getWinner()); // noWinner

	}

	@Test
	public void allAgentsRevealedTestBlueWinner() {
		Game game = new Game();
		Board board = game.getBoard();
		Location[][] arr = board.getLocations();
		for (int i = 0; i < 5; i++) {
			for (int n = 0; n < 5; n++) {
				Location loc = arr[i][n];
				if (loc.getPerson().getAgentType() == 0) {
					loc.setVisible(true);
				}
			}
		}
		assertEquals("There is no winner when there should be ", true, board.allAgentsRevealed());
		assertEquals("The winner should be blue", "Blue", game.getWinner());
	}

	@Test
	public void allAgentsRevealedTestRedWinner() {
		Game game = new Game();
		Board board = game.getBoard();
		Location[][] arr = board.getLocations();
		for (int i = 0; i < 5; i++) {
			for (int n = 0; n < 5; n++) {
				Location loc = arr[i][n];
				if (loc.getPerson().getAgentType() == 1) {
					loc.setVisible(true);
				}
			}
		}
		assertEquals("There is no winner when there should be one ", true, board.allAgentsRevealed());
		assertEquals("The winner should be red", "Red", game.getWinner());
	}

	@Test
	public void testAssassinRevealedByRed() {
		Board b = new Board();
		Game game = new Game();
		Location[][] arr = b.getLocations();

		for (int i = 0; i < 5; i++) {
			for (int n = 0; n < 5; n++) {
				Location loc = arr[i][n];
				if (loc.getPerson().getAgentType() == 2) { // if location is visible and it's an assassin.
					loc.setVisible(true);
				}
			}
			game.setTurn(1); // set turn to Red

			assertEquals(
					"The game determined the winner incorrectly. the winner shouldn't be the Red Team who revealed the assassin",
					true, "Red" != game.getWinner());

		}
	}

	@Test
	public void testAssassinRevealedByBlue() {
		Board b = new Board();
		Game game = new Game();
		Location[][] arr = b.getLocations();
		
		for (int i = 0; i < 5; i++) {
			for (int n = 0; n < 5; n++) {
				Location loc = arr[i][n];
				if (loc.getPerson().getAgentType() == 2) { // if location is visible and it's an assassin.
					loc.setVisible(true);
				}
			}
			game.setTurn(1); // set turn to Blue

			assertEquals(
					"The game determined the winner incorrectly. the winner shouldn't be the Blue Team who revealed the assassin",
					true, "Blue" != game.getWinner());

		}
	}

}
