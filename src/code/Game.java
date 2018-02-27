package code;

public class Game {

	private static Board board;
	private static int turn;
	private static String winner; 
	static int RED = 1; static int BLUE = 0;
	
	public Game(){
		main(null);
	}
	public static void main(String[] args){
		setBoard(new Board());
		turn = RED;
		winner = "";
		
	}
	
	
	public void setTurn(int turn){
		if(turn == 0 || turn ==1)
			this.turn = turn;
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
