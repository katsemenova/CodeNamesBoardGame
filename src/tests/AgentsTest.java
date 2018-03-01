package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.Board;
import code.Location;

public class AgentsTest {

	// First, if person has int value from 0-3 and has string value
	// Second, make location instance with params. Ensure assigned correctly.
	// Codename, person, boolean cannot be null
	// Third, make instance of Board, keep track of number of red, blue, assassins,
	// bystanders

	// @Author Hollis Pauquette, Aaron Kong
	
	private Board b = new Board();

	@Test
	public void testPersonsForNull() {
		Location[][] theLocations = b.getLocations();
		for (int i = 0; i < theLocations.length; i++) {
			for (int j = 0; j < theLocations[0].length; j++) {
				assertTrue("The agent codename is not set",theLocations[i][j].getPerson().getAgentName() != null);
				assertTrue("Agents aren't assigned a correct bound for the agentType field",theLocations[i][j].getPerson().getAgentType() != null
						&& theLocations[i][j].getPerson().getAgentType() <= 3);
				assertTrue("Agents aren't assigned a correct bound for the agentType field",theLocations[i][j].getPerson().getAgentType() != null
						&& theLocations[i][j].getPerson().getAgentType() >= 0);
				assertTrue("The location instance is not defined",theLocations[i][j] != null);
			}
		}

	}

	@Test
	public void testRedAgents() {
		int rCount = 0;
		Location[][] theLocations = b.getLocations();
		for (int i = 0; i < theLocations.length; i++) {
			for (int j = 0; j < theLocations[0].length; j++) {
				if (theLocations[i][j].getPerson().getAgentType() == 1) {
					rCount += 1;
				}

			}
		}
		assertEquals("There is an incorrect number of red agents",9, rCount);
	}

	@Test
	public void testBlueAgents() {
		int bCount = 0;
		Location[][] theLocations = b.getLocations();
		for (int i = 0; i < theLocations.length; i++) {
			for (int j = 0; j < theLocations[0].length; j++) {
				if (theLocations[i][j].getPerson().getAgentType() == 0) {
					bCount += 1;
				}

			}
		}
		assertEquals("There is an incorrect number of blue agents",8, bCount);
	}

	@Test
	public void testAssassins() {
		int aCount = 0;
		Location[][] theLocations = b.getLocations();
		for (int i = 0; i < theLocations.length; i++) {
			for (int j = 0; j < theLocations[0].length; j++) {
				if (theLocations[i][j].getPerson().getAgentType() == 2) {
					aCount += 1;
				}

			}
		}
		assertEquals("There is an incorrect number of assassins",1, aCount);
	}

	@Test
	public void testBystanders() {
		int byCount = 0;
		Location[][] theLocations = b.getLocations();
		for (int i = 0; i < theLocations.length; i++) {
			for (int j = 0; j < theLocations[0].length; j++) {
				if (theLocations[i][j].getPerson().getAgentType() == 3) {
					byCount += 1;
				}

			}
		}
		assertEquals("There is an incorrect number of bystanders",7, byCount);
	}

	@Test
	public void testRandomAssigning(){
		Board b = new Board();
		Location[][] theLocationsOne = b.getLocations();
		b.assignLocations();
		Location[][] theLocationsTwo = b.getLocations();
		boolean notTheSame = false;
		
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 5; j++){
				if(theLocationsOne[i][j].getPerson().getAgentType()!=
						theLocationsTwo[i][j].getPerson().getAgentType())
					notTheSame = true;
			}
		}
		assertEquals("The agents are not randomly assigned, the boards are the same", true, notTheSame);
	}
	
}

