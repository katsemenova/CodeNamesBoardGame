package code;

public class Game {

	//@author: Kateryna Semenova
	
	/*
	 * Field to hold the board created for the game
	 */
	private static Board board;
	/*
	 * int that keeps track of who's turn it is. RED = 1, BLUE = 0  defined below
	 */
	private static int turn;
	/*
	 * Instantiated to an empty string, when a winning state occurs states the winner.
	 */
	private static String winner; 
	/*
	 * final constants, to define who's turn is when, done for better readability
	 */
	private final static int RED = 1; private final static int BLUE = 0;
	
	/*
	 * Constructor that runs the main method when board is created
	 */
	public Game(){
		main(null);
	}
	
	/**
	   * Main method that creates the board, sets winner to empty string and makes sure the game begins on Red teams turn
	   *
	   * @param String[] args
	   * @return none
	   */
	public static void main(String[] args){
		setBoard(new Board());
		turn = RED;
		winner = "";
		
	}
	
	
	public void setTurn(int turn){
		if(turn == BLUE || turn == RED)
			Game.turn = turn;
	}
	
	public static void setWinner(String win){
		winner = win;
	}
	public String getWinner(){
		return winner;
	}
	public static int getTurn(){
		return turn;
	}

	public Board getBoard() {
		return board;
	}

	public static void setBoard(Board board) {
		Game.board = board;
	}
	
	
	
}
