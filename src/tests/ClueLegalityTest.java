package tests;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import code.Board;

/*
 * @author Aaron & Hollis
 */
public class ClueLegalityTest {
	@Test
	public void legaClueTestAgentName(){
				
		Board b = new Board();
		
		List<String> agentNames = b.getCodenamesUsed();
		
		assertEquals(false, b.legalClue(agentNames.get(1)));
		assertEquals("Agent is not revealed yet, so using their name is not legal ",false, b.legalClue(agentNames.get(0)));


}
	@Test
	public void legalClueEmptyStringTest(){
		Board b = new Board();
		assertEquals(false, b.legalClue(""));
	}
	
	@Test
	public void legalClueVisibleNameTest(){
		Board b = new Board();
		b.getLocations()[1][1].setVisible(true);
		String testName = b.getLocations()[1][1].getPerson().getAgentName();
		assertEquals(true,b.legalClue(testName));
		
		
		
	}

}
