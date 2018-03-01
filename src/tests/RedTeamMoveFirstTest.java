package tests;

/* @Author Aaron and Xiyin */

import static org.junit.Assert.*;

import org.junit.Test;

import code.Board;
import code.Game;
import code.Location;
import code.Person;

public class RedTeamMoveFirstTest {
	@Test
	public void redMoveFirst(){
		Game game = new Game(); 
		assertEquals("Red team moves first.",1,game.getTurn());
		
	}
	@Test
	public void boardLocationsCreatedCorrectlyTest(){
		Game game = new Game();
		Board b = game.getBoard();
		Location[][] locations=b.getLocations();
		int index=0;
		for (int i = 0; i < locations.length; i++) {
			for (int j = 0; j < locations[0].length; j++) {
				Location loc = locations[i][j];
				if(loc != null){
					index++;
					assertEquals("location is visible when it shouldnt be ", false, loc.isVisible());
					assertEquals("Person is null for a location",true,loc.getPerson()!=null);
					assertEquals("Location is not assign a codename",true, loc.getLocationCodename()!=null);
				}
			}	
		}
		assertEquals("The are not 25 locations initialized",25,index);
	}
}
