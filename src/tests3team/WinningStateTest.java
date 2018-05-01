package tests3team;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import code.Game;
import code.Location;

public class WinningStateTest {

	
	
	@Test
	public void blueWinTest() {
		Game game = new Game(false);
		Location[][] arr = game.getBoard().getLocations();

		for (int i = 0; i < 5; i++) {
			for (int n = 0; n < 5; n++) {
				Location loc = arr[i][n];
				if (loc.getPerson().getAgentType() == 0) {// if location is visible and it's blue.
					loc.setVisible(true);
				}
			}
		}
		game.getBoard().checkWinningState();
		assertEquals("Blue",Game.getWinner());
	}
	
	@Test
	public void redWinTest() {
		Game game = new Game(false);
		Location[][] arr = game.getBoard().getLocations();

		for (int i = 0; i < 5; i++) {
			for (int n = 0; n < 5; n++) {
				Location loc = arr[i][n];
				if (loc.getPerson().getAgentType() == 1) {// if location is visible and it's red.
					loc.setVisible(true);
				}
			}
		}
		game.getBoard().checkWinningState();
		assertEquals("Red",Game.getWinner());
	}
	
	@Test
	public void greenWinTest() {
		Game game = new Game(false);
		Location[][] arr = game.getBoard().getLocations();
		for (int i = 0; i < 5; i++) {
			for (int n = 0; n < 5; n++) {
				Location loc = arr[i][n];
				if (loc.getPerson().getAgentType() == 4) {// if location is visible and it's green.
					loc.setVisible(true);
				}
			}
		}
		game.getBoard().checkWinningState();
		assertEquals("Green",Game.getWinner());
	}

}
