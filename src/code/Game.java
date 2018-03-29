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
	private final static int BLUE = 0; private final static int RED = 1; 
	/*
	 * either "Players" if they are guessing the agents or "spymaster"
	 */
	private static String control;
	/*
	 * Constructor that runs the main method when board is created
	 */
	public Game(){
		setBoard(new Board());
		turn = RED;
		winner = "";
		control = "Spymaster";
	}
	
	/*
	 * changes game from spymaster to players move of the team whos turn it is
	 */
	public void changeControl(){
		if(control.equals("Spymaster"))
			control = "Players";
		else
			control = "Spymaster";
	}
	public void setTurn(int turn){
		if(turn == BLUE || turn == RED)
			Game.turn = turn;
	}
	public String getTurnString(){
		if(turn == BLUE)
			return "Blue";
		else return "Red";
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
	/*
	 * has to be changed to actual logic this is for look purposes
	 */
	public String getControl(){
		return control;
	}
}
