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
	private final static int BLUE = 0; private final static int RED = 1; private final static int GREEN = 4;
	/*
	 * either "Players" if they are guessing the agents or "spymaster"
	 */
	private static String control;
	/*
	 * a boolean that when true means its a red/blue team game 
	 * when false its a blue/green/red team game
	 */
	private static boolean twoTeam;
	/*
	 * booleans that will be set to true when the correstponding team reveals an assassin
	 */
	private static boolean redAssassin, blueAssassin, greenAssassin;
	/*
	 * Constructor that runs the main method when board is created
	 * it automatically creates a two team game
	 */
	public Game(){
		twoTeam = true;
		setBoard(new Board());
		turn = RED;
		winner = "";
		redAssassin = false;
		blueAssassin = false;
		greenAssassin = false;
		control = "Spymaster";
	}
	/*
	 * Constructor that runs the main method when board is created
	 * @param boolean determines wheather it is a two team or a three team game;
	 */
	public Game(boolean set){
		twoTeam = set;
		setBoard(new Board());
		turn = RED;
		winner = "";
		redAssassin = false;
		blueAssassin = false;
		greenAssassin = false;
		control = "Spymaster";
		}
	/*
	 * @return boolean {@code true} when the red team reveals the assassin 
	 */
	public static boolean isRedAssassin() {
		return redAssassin;
	}
	/*
	 * @param boolean that sets if the red team has revealed the assassin or not 
	 */
	public static void setRedAssassin(boolean redAssassin) {
		Game.redAssassin = redAssassin;
	}
	/*
	 * @return boolean {@code true} when the blue team reveals the assassin 
	 */
	public static boolean isBlueAssassin() {
		return blueAssassin;
	}
	/*
	 * @param boolean that sets if the blue team has revealed the assassin or not 
	 */
	public static void setBlueAssassin(boolean blueAssassin) {
		Game.blueAssassin = blueAssassin;
	}
	/*
	 * @return boolean {@code true} when the green team reveals the assassin 
	 */
	public static boolean isGreenAssassin() {
		return greenAssassin;
	}
	/*
	 * @param boolean that sets if the green team has revealed the assassin or not 
	 */
	public static void setGreenAssassin(boolean greenAssassin) {
		Game.greenAssassin = greenAssassin;
	}

	/*
	 * changes game from spymaster to players move of the team whos turn it is
	 */
	public static void changeControl(){
		if(control.equals("Spymaster"))
			control = "Players";
		else if(control.equals("Players"))
			control = "Spymaster";

	}
	/*
	 * changes turn from whatever it is currently
	 */
	public static void switchTeamTurn(){
		//turn = board.getNextTurn();
		if(twoTeam){
			if(turn == BLUE)
				Game.turn = RED;
			else
				Game.turn = BLUE;
		}else{
			if(turn == RED){
				if(!blueAssassin)
					Game.turn = BLUE;
				else 
					Game.turn = GREEN;
			}else if(turn == BLUE){
				if(!greenAssassin)
					Game.turn =GREEN;
				else
					Game.turn = RED;
			}else if(turn == GREEN)
				if(!redAssassin)
					Game.turn = RED;
				else 
					Game.turn = BLUE;
				
		}
		
	}
	/*
	 * When an agents reveals an assassin this method gets called to set the corresponding boolean to true
	 * @param int is the agent's number
	 */
	public static void revealedAssassin(int agent){
		if(agent == RED)
			redAssassin = true;
		else if(agent == BLUE)
			blueAssassin = true;
		else if(agent == GREEN)
			greenAssassin = true;
		
	}
	/*
	 * Converts the turn int to a string value
	 * 
	 * @return a string stating which team's turn it is
	 */
	public String getTurnString(){
		if(turn == BLUE)
			return "Blue";
		else if(turn == GREEN)
			return "Green";
		else 
			return "Red";
	}
	
	public static void setWinner(String win){
		winner = win;
	}
	public static String getWinner(){
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
	/*
	 * sets's who's turn it is based on the 
	 * @param int which is the number corresponding to the team who's turn it is
	 */
	public void setTurn(int i) {
		if(i == BLUE)
			Game.turn = BLUE;
		else if(i == GREEN)
			Game.turn = GREEN;
		else
			Game.turn = RED;
	}

	public static boolean isTwoTeamGame() {
		return twoTeam;
	}

	@SuppressWarnings("static-access")
	public void setTwoTeamGame(boolean twoTeam) {
		this.twoTeam = twoTeam;
	}
}
