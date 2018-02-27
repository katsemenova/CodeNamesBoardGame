package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.Board;
import code.Location;

public class AgentsTest {
	
	//First, if person has int value from 0-3 and has string value
	//Second, make location instance with params. Ensure assigned correctly.
	//Codename, person, boolean cannot be null
	//Third, make instance of Board, keep track of number of red, blue, assassins, bystanders 
	
	// @Author Hollis Pauquette, Aaron Kong
	
	@Test
	public void testPersonsForNull() { 
		Location[][] theLocations;
		Board b = new Board();
		b.setFile("testfiles/codenames.txt");
		b.assignLocations();
		theLocations = b.getLocations();
		for (int i = 0; i < theLocations.length; i++) {
			for (int j = 0; j < theLocations[0].length; j++) {
				
				assertTrue(theLocations[i][j] != null);
			}
		}
		
	}
	
	@Test
	public void testRedAgents() {
		int rCount = 0;
		Location[][] theLocations;
		Board b = new Board();
		b.setFile("testfiles/codenames.txt");
		b.assignLocations();
		theLocations = b.getLocations();
		for (int i = 0; i < theLocations.length; i++) {
			for (int j = 0; j < theLocations[0].length; j++) {
				if(theLocations[i][j].getPerson().getAgentType() == 1) {
					rCount += 1;
				}
				
			}
		}
		assertEquals(9,rCount);
	}

}
