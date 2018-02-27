package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
//@author Xiyin Liang, Kateryna Semenova

import code.Board;
import code.Game;
import code.Location;

public class WinningStateTest {
	@Test
	public void checkForAssassinTestNoAssassin(){
		Game game = new Game();
		Board board = game.getBoard();
		Location[][] arr= board.getLocations();
		
		boolean assassinExists = false;
		for(int i=0;i<5;i++){
			for(int n=0;n<5;n++){
				Location loc =arr[i][n];
				if(loc.isVisible()&&loc.getPerson().getAgentType()==2) {//if location is visible and it's an assassin.
					assassinExists = true;
					
			}
			}
		}
		
		assertEquals("checkForAssassin should return false as no assassin exists", assassinExists, board.checkForAssassin());
	}
	
	@Test
	public void checkForAssassinTestWithAssassin(){
		Game game = new Game();
		Board board = game.getBoard();
		Location[][] arr=board.getLocations();
		boolean assassinExists = false;
		for(int i=0;i<5;i++){
			for(int n=0;n<5;n++){
				Location loc =arr[i][n];
				if(loc.getPerson().getAgentType()==2) {//if location is visible and it's an assassin.
					loc.setVisible(true);
					assassinExists = true;	
			}
			}
		}
		
		assertEquals("checkForAssassin should return false as no assassin exists", assassinExists, board.checkForAssassin());
	}
	@Test
	public void allAgentsRevealedTestNoWinner(){
		Game game = new Game();
		System.out.println(game.getBoard() == null);
		Board board = game.getBoard();
		
		//Location[][] arr=board.getLocations();

		assertEquals("There should be no winner since no agents are revealed",false, board.allAgentsRevealed());
		assertEquals("There should be no winner declared","",game.getWinner()); //noWinner
		
	}
	@Test
	public void allAgentsRevealedTestBlueWinner(){
		Game game = new Game();
		Board board = game.getBoard();
		Location[][] arr=board.getLocations();
		int count=0;
		for(int i=0;i<5;i++){
			for(int n=0;n<5;n++){
				Location loc =arr[i][n];
				if(loc.getPerson().getAgentType()==0){
					loc.setVisible(true);
					count++;
				}
			}
		}
		assertEquals("There is no winner when there should be ",true, board.allAgentsRevealed());
		assertEquals("The winner should be blue","Blue",game.getWinner());
	}

	@Test
	public void allAgentsRevealedTestRedWinner(){
		Game game = new Game();
		Board board = game.getBoard();
		Location[][] arr=board.getLocations();
		for(int i=0;i<5;i++){
			for(int n=0;n<5;n++){
				Location loc =arr[i][n];
				if(loc.getPerson().getAgentType()==1){
					loc.setVisible(true);
				}
			}
		}
		assertEquals("There is no winner when there should be one ",true, board.allAgentsRevealed());
		assertEquals("The winner should be red","Red",game.getWinner());
	}

}
