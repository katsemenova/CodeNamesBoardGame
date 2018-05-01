package tests3team;

import static org.junit.Assert.*;

import org.junit.Test;

import code.Game;
import code.Location;
public class AssassinTest {

	@Test
	public void greenTest() {
		Game game = new Game(false);
		Location[][] arr = game.getBoard().getLocations();
		int m = 1;
		for (int i = 0; i < 5; i++) {
			for (int n = 0; n < 5; n++) {
				Location loc = arr[i][n];
				if (loc.getPerson().getAgentType() == 2	) {// if location is visible and it's blue.
					loc.setVisible(true);
					game.getBoard().checkWinningState();
					Game.switchTeamTurn();
					Game.revealedAssassin(m);
					m+=1;
				}
				
			}
		}
		game.getBoard().checkWinningState();
		assertEquals(false,game.getBoard().checkWinningState());
	}
	
	@Test
	public void blueTest() {
		Game game = new Game(false);
		Location[][] arr = game.getBoard().getLocations();
		int m = 1;
		Game.switchTeamTurn();
		Game.switchTeamTurn();
		for (int i = 0; i < 5; i++) {
			for (int n = 0; n < 5; n++) {
				Location loc = arr[i][n];
				if (loc.getPerson().getAgentType() == 2	) {// if location is visible and it's blue.
					loc.setVisible(true);
					game.getBoard().checkWinningState();
					Game.switchTeamTurn();
					Game.revealedAssassin(m);
					m+=1;
				}
				
			}
		}
		game.getBoard().checkWinningState();
		assertEquals(false,game.getBoard().checkWinningState());
	}

	@Test
	public void redTest() {
		Game game = new Game(false);
		Location[][] arr = game.getBoard().getLocations();
		int m = 1;
		Game.switchTeamTurn();
		for (int i = 0; i < 5; i++) {
			for (int n = 0; n < 5; n++) {
				Location loc = arr[i][n];
				if (loc.getPerson().getAgentType() == 2	) {// if location is visible and it's blue.
					loc.setVisible(true);
					game.getBoard().checkWinningState();
					Game.switchTeamTurn();
					Game.revealedAssassin(m);
					m+=1;
				}
				
			}
		}
		game.getBoard().checkWinningState();
		assertEquals(false,game.getBoard().checkWinningState());
	}


}
