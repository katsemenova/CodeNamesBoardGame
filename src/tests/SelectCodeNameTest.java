package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import code.Board;
import code.Game;
import code.Location;

public class SelectCodeNameTest {
	
	
	@Test
	public void selectCodeNameDecrementCountTest(){
		Game game = new Game();
		Board board = game.getBoard();
		String codeName = board.getCodenamesUsed().get(3);
		board.selectCodeName(codeName);
		int initialCount = board.getCount();
		assertEquals("The count is not decremented correctly",initialCount--,board.getCount());
	}
	
	@Test
	public void selectCodeNameWrongCodenameTest(){
		Game game = new Game();
		Board board = game.getBoard();
		String codeName = "a";
		board.selectCodeName(codeName);
		int initialCount = board.getCount();
		assertEquals("The invalid codeName shouldn't decrement the count",initialCount,board.getCount());
	}
	
	@Test (expected = NullPointerException.class)
	public void selectCodeNameNullTest(){
		Game game = new Game();
		Board board = game.getBoard();
		String codeName = null;
		board.selectCodeName(codeName);
		int initialCount = board.getCount();
		assertEquals("Exception not thrown",initialCount,board.getCount());
	}
	
	@Test
	public void selectCodeNameUpdateLocationTest(){
		Game game = new Game();
		Board board = game.getBoard();
		Location location = board.getLocations()[1][1];
		boolean initialVisibility = location.isVisible();
		board.selectCodeName(location.getPerson().getAgentName());
		assertEquals("Location visibility is not updated correctly",!initialVisibility, location.isVisible());
		
	}
	
	@Test
	public void selectCodeNameReturnsCorrectBooleanTest(){
		Game game = new Game();
		Board board = game.getBoard();
		Location[][] loc = board.getLocations();
		Location assassin = null;
		Location redAgent = null;
		Location blueAgent = null;
		Location bystander = null;
		
		for(int i = 0; i<5; i++){
			for(int j = 0;j<5; j++){
				if(loc[i][j].getPerson().getAgentType() == 0)
					blueAgent = loc[i][j];
				if(loc[i][j].getPerson().getAgentType() == 1)
					redAgent = loc[i][j];
				if(loc[i][j].getPerson().getAgentType() == 2)
					bystander = loc[i][j];
				if(loc[i][j].getPerson().getAgentType() == 3)
					assassin = loc[i][j];
			}
		}
		
		int turn = game.getTurn(); //1 = red, 0 = blue
		
		if(turn == 1){
			assertEquals("It was red's turn, and they selected their own agent",
				true, board.selectCodeName(redAgent.getLocationCodename()));
			assertEquals("It was red's turn, and they selected a blue agent",
					false, board.selectCodeName(blueAgent.getLocationCodename()));
				
		}
		game.setTurn(0);
		blueAgent.setVisible(false);
		redAgent.setVisible(false);
		if(turn == 0){
			assertEquals("It was blue's turn, and they selected their own agent",
				true, board.selectCodeName(blueAgent.getLocationCodename()));
			assertEquals("It was blue's turn, and they selected a red agent",
					false, board.selectCodeName(redAgent.getLocationCodename()));
				
		}
		assertEquals("Bystander was selected and its neither teams agent",
				false, board.selectCodeName(bystander.getLocationCodename()));
	
		assertEquals("Assassin was selected and its neither teams agent",
				false, board.selectCodeName(assassin.getLocationCodename()));
	}

}
