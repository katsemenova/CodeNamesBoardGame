package tests;

import org.junit.Test;
//@author Xiyin Liang, Kateryna Semenova

public class WinningStateTest {
	@Test
	public void checkForAssassinTest(){
		Game game = new Game();
		board = game.board;
		Location[][] arr=board.location;
		for(int i=0;i<5;i++){
			for(int n=0;n<5;n++){
				Location loc =arr[i][n];
				if(loc.visible&&loc.person.value==2) {//if location is visible and it's an assassin.
					assertEquals(true, game.winner!=game.turn);
			}
			}
		}
	}
	@Test
	public void revealedRedTest(){
		Game game = new Game();
		board = game.board;
		Location[][] arr=board.location;
		int count=0;
		for(int i=0;i<5;i++){
			for(int n=0;n<5;n++){
				Location loc =arr[i][n];
				if(loc.visible&&loc.person.value==1){
					count++;
				}
			}
		}
		if(count==9){
			assertEquals("Red",game.winner);
		}
	}
	@Test
	public void revealedBlueTest(){
		Game game = new Game();
		board = game.board;
		Location[][] arr=board.location;
		int count=0;
		for(int i=0;i<5;i++){
			for(int n=0;n<5;n++){
				Location loc =arr[i][n];
				if(loc.visible&&loc.person.value==0){
					count++;
				}
			}
		}
		if(count==8){
			assertEquals("Blue",game.winner);
		}
	}

}
