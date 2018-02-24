package code;

public class Game {

	private static Board board;
	private static int turn;
	private static String winner; 
	static int RED = 0; static int BLUE = 1;
	
	public static void main(String[] args){
		board = new Board();
		turn = RED;
		winner = null;
	}
	
	public void setTurn(int turn){
		if(turn == 0 || turn ==1)
			this.turn = turn;
	}
	
	public void setWinner(String winner){
		this.winner = winner;
	}
	
	public int getTurn(){
		return turn;
	}
	
	
	
}
