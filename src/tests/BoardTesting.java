package tests;

import org.junit.Test;

//@author Kateryna Semenova

public class BoardTesting {
	
	
	@Test
	public void createBoardTest(){
		Board board = new Board();
		Location[][] arr = board.locations;
		assertEquals(true, arr.isNotNull());
		assertEquals(25, arr.length*arr[0].length);
		
		 
	}

}
