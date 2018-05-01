package tests3team;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import code.Board;
import code.Game;
import code.Location;

public class TeamTest {
	
	private Game game = new Game(false);
	
	private Board b = new Board();
	
	@Before
	public void agentTest() {
		Location[][] theLocations = b.getLocations();
		
		for (int i = 0; i < theLocations.length; i++) {
			for (int j = 0; j < theLocations[0].length; j++) {
				assertTrue("The agent codename is not set",theLocations[i][j].getPerson().getAgentName() != null);
				assertTrue("Agents aren't assigned a correct bound for the agentType field",theLocations[i][j].getPerson().getAgentType() != null
						&& theLocations[i][j].getPerson().getAgentType() <= 4);
				assertTrue("Agents aren't assigned a correct bound for the agentType field",theLocations[i][j].getPerson().getAgentType() != null
						&& theLocations[i][j].getPerson().getAgentType() >= 0);
				assertTrue("The location instance is not defined",theLocations[i][j] != null);
			}
		}
	}
	
//	6 Red Agents, 5 Blue Agents, 5 Green Agents, 7 Innocent Bystanders, and 2 Assassins
	
	@Test
	public void testRedAgents() {
		int rCount = 0;
		Location[][] theLocations = game.getBoard().getLocations();
		for (int i = 0; i < theLocations.length; i++) {
			for (int j = 0; j < theLocations[0].length; j++) {
				if (theLocations[i][j].getPerson().getAgentType() == 1) {
					rCount += 1;
				}

			}
		}
		assertEquals("There is an incorrect number of red agents",6, rCount);
	}

	@Test
	public void testBlueAgents() {
		int bCount = 0;
		Location[][] theLocations = game.getBoard().getLocations();
		for (int i = 0; i < theLocations.length; i++) {
			for (int j = 0; j < theLocations[0].length; j++) {
				if (theLocations[i][j].getPerson().getAgentType() == 0) {
					bCount += 1;
				}

			}
		}
		assertEquals("There is an incorrect number of blue agents",5, bCount);
	}
	
	@Test
	public void testGreenAgents() {
		int bCount = 0;
		Location[][] theLocations = game.getBoard().getLocations();
		for (int i = 0; i < theLocations.length; i++) {
			for (int j = 0; j < theLocations[0].length; j++) {
				if (theLocations[i][j].getPerson().getAgentType() == 4) {
					bCount += 1;
				}

			}
		}
		assertEquals("There is an incorrect number of blue agents",5, bCount);
	}

	@Test
	public void testAssassins() {
		int aCount = 0;
		Location[][] theLocations = game.getBoard().getLocations();
		for (int i = 0; i < theLocations.length; i++) {
			for (int j = 0; j < theLocations[0].length; j++) {
				if (theLocations[i][j].getPerson().getAgentType() == 2) {
					aCount += 1;
				}

			}
		}
		assertEquals("There is an incorrect number of assassins",2, aCount);
	}
	@Test
	public void testBystandersAgents() {
		int bCount = 0;
		Location[][] theLocations = game.getBoard().getLocations();
		for (int i = 0; i < theLocations.length; i++) {
			for (int j = 0; j < theLocations[0].length; j++) {
				if (theLocations[i][j].getPerson().getAgentType() == 3) {
					bCount += 1;
				}

			}
		}
		assertEquals("There is an incorrect number of blue agents",7, bCount);
	}

	
}
