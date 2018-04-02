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
	public static void changeControl(){
		System.out.println(control + " is playing");
		if(control.equals("Spymaster")){
			control = "Players";
			System.out.println("s->p");
		}
		else if(control.equals("Players")){
			control = "Spymaster";
			System.out.println("p->s");
		}
		System.out.println(control + " is playing");
		
		System.out.println("finished");
	}
	public static void switchTeamTurn(){
		if(turn == BLUE)
			Game.turn = RED;
		else
			Game.turn = BLUE;
		
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

	public void setTurn(int i) {
		if(i == BLUE)
			Game.turn = BLUE;
		else
			Game.turn = RED;
	}
}
