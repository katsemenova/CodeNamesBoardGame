package tests3team;

import static org.junit.Assert.*;

import org.junit.Test;

import code.Game;
import code.Location;

public class NextTurnTest {

	@Test
	public void twoTeamRotation() {
		Game g = new Game();
		int currentTurn = Game.getTurn();
		Location[][] loc = g.getBoard().getLocations();
		String code="";
		for(int i = 0; i< 5; i++){
			for(int j=0; j<5;j++){
				int agent = loc[i][j].getPerson().getAgentType();
				if(agent!=2&&agent != currentTurn){
					code = loc[i][j].getLocationCodename();
					break;
				}
			}
		}
		//change from red to blue
		assertEquals("game didnt start off with red's turn",true,currentTurn==1);
		g.getBoard().selectCodeName(code);
		assertEquals("Turn didnt chnage to blue",true,0==Game.getTurn());
		
		currentTurn = Game.getTurn();
		for(int i = 0; i< 5; i++){
			for(int j=0; j<5;j++){
				int agent = loc[i][j].getPerson().getAgentType();
				if(agent!=2&&agent != currentTurn){
					code = loc[i][j].getLocationCodename();
					break;
				}
			}
		}
		g.getBoard().selectCodeName(code);
		assertEquals("Turn didnt chnage to red",true,1==Game.getTurn());
		
	}
	public void threeTeamRotation() {
		Game g = new Game(false);
		int currentTurn = Game.getTurn();
		Location[][] loc = g.getBoard().getLocations();
		String code="";
		for(int i = 0; i< 5; i++){
			for(int j=0; j<5;j++){
				int agent = loc[i][j].getPerson().getAgentType();
				if(agent!=2&&agent != currentTurn){
					code = loc[i][j].getLocationCodename();
					break;
				}
			}
		}
		//change from red to blue
		assertEquals("game didnt start off with red's turn",true,currentTurn==1);
		g.getBoard().selectCodeName(code);
		assertEquals("Turn didnt chnage to blue",true,0==Game.getTurn());
		
		currentTurn = Game.getTurn();
		for(int i = 0; i< 5; i++){
			for(int j=0; j<5;j++){
				int agent = loc[i][j].getPerson().getAgentType();
				if(agent!=2&&agent != currentTurn){
					code = loc[i][j].getLocationCodename();
					break;
				}
			}
		}
		//change from blue to green
		g.getBoard().selectCodeName(code);
		assertEquals("Turn didnt chnage to green",true,4==Game.getTurn());
		

		currentTurn = Game.getTurn();
		for(int i = 0; i< 5; i++){
			for(int j=0; j<5;j++){
				int agent = loc[i][j].getPerson().getAgentType();
				if(agent!=2&&agent != currentTurn){
					code = loc[i][j].getLocationCodename();
					break;
				}
			}
		}
		//change from green to red
		g.getBoard().selectCodeName(code);
		assertEquals("Turn didnt chnage to blue",true,1==Game.getTurn());
	}
	public void threeTeamRotationAssassin() {
		Game g = new Game(false);
		int currentTurn = Game.getTurn();
		Location[][] loc = g.getBoard().getLocations();
		String codeRed="";
		String codeAssassin = "";
		String codeBlue = "";
		String codeGreen ="";
		for(int i = 0; i< 5; i++){
			for(int j=0; j<5;j++){
				int agent = loc[i][j].getPerson().getAgentType();
				if(agent ==0 ){
					 codeBlue= loc[i][j].getLocationCodename();
				}
				if(agent ==2 ){
					codeAssassin= loc[i][j].getLocationCodename();
				}
				if(agent ==1 ){
					codeRed= loc[i][j].getLocationCodename();
				}
				if(agent ==4 ){
					codeGreen= loc[i][j].getLocationCodename();
				}
			}
		}
		//change from red to blue
		assertEquals("game didnt start off with red's turn",true,currentTurn==1);
		g.getBoard().selectCodeName(codeBlue);
		assertEquals("Turn didnt chnage to blue",true,0==Game.getTurn());
		
		//blue team selects assassin so they will not be in rotation
		currentTurn = Game.getTurn();
		g.getBoard().selectCodeName(codeAssassin);
		assertEquals("Turn didnt chnage to green",true,4==Game.getTurn());
		
		//green continues rotation to red
		currentTurn = Game.getTurn();
		g.getBoard().selectCodeName(codeRed);
		assertEquals("Turn didnt chnage to red",true,1==Game.getTurn());

		//red skipped over blue went to green
		currentTurn = Game.getTurn();
		g.getBoard().selectCodeName(codeGreen);
		assertEquals("Turn didnt chnage to green",true,4==Game.getTurn());
	}
}
