package tests;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import code.Board;
import code.Game;

/*
 * @author Aaron & Hollis
 */
public class ClueLegalityTest {
	@Test
	public void legaClueTestAgentName(){
		Game g = new Game();
		Board b = g.getBoard();
		
		List<String> agentNames = b.getCodenamesUsed();
		assertEquals("Agent is not revealed yet, so using their name is not legal ",false, b.legalClue(agentNames.get(2)));
		assertEquals("Agent is not revealed yet, so using their name is not legal ",false, b.legalClue(agentNames.get(3)));


}
	@Test
	public void legalClueEmptyStringTest(){
		Board b = new Board();
		assertEquals("An empty string shouldn't be a clue",false, b.legalClue(""));
	}
	
	@Test
	public void legalClueVisibleNameTest(){
		Board b = new Board();
		b.getLocations()[1][1].setVisible(true);
		String testName = b.getLocations()[1][1].getPerson().getAgentName();
		assertEquals("A codename of a revealed agent is a valid clue",true,b.legalClue(testName));
		
		
		
	}

}
