package tests;

/* @Author Aaron and Xiyin */

import static org.junit.Assert.*;

import org.junit.Test;

import code.Location;

public class RedTeamMoveFirstTest {
	@Test
	public void RedMoveFirst(){
		RedTeamMoveFirst a = new RedTeamMoveFirst();
		assertEquals("Red team moves first.",0,a.equals(0));
		assertFalse("Red team always moves first.",1,a.equals(0));
		
	}
	@Test
	public void LocationTest(){
		Location a = new Location("AFRICA", 0);
		Board b = new Board();
		String codename = "AFRICA";
		
		int person = 0;
		boolean hidden = true; /*true means not revealed, false means revealed */
		for(int i=0;i<b.locations.length;i++){/*for 25 instances */
			b.locations.getAgentName();
		}
		assertEquals("AFRICA",a.getAgentName());
		assertEquals(0,a.getAgentType());
		assertTrue(a.hidden,a.isVisible());
	}

}
