package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AgentsTest {
	
	//First, if person has int value from 0-3 and has string value
	//Second, make location instance with params. Ensure assigned correctly.
	//Codename, person, boolean cannot be null
	//Third, make instance of Board, keep track of number of red, blue, assassins, bystanders 
	
	// @Author Hollis Pauquette, Aaron Kong
	
	@Test
	public void testPersons() { 
		int bCount = 0;
		int rCount = 0;
		int aCount = 0;
		int byCount = 0;
		Board b = new Board();
		for (int i = 0; i < b.locations.length; i++) {
			for (int j = 0; j < b.locations[0].length; j++) {
				if(b.locations[i][j].getName() == "Blue") {
					bCount += 1;
				}
				if(b.locations[i][j].getName() == "Red") {
					rCount += 1;
				}
				if(b.locations[i][j].getName() == "Assassin") {
					aCount += 1;
				}
				if(b.locations[i][j].getName() == "Bystander") {
					byCount += 1;
				}
				assertTrue(b.locations[i][j] != null);
				assertEquals(8, bCount);
				assertEquals(9, rCount);
				assertEquals(1, aCount);
				assertEquals(7, byCount);
			}
		}
		
	}

}
