package code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * Instances of this class represent the game board in it's current state
 * 
 * @author Hollis Pauquette
 * @author Kateryna Semenova
 */

public class Board {

	/*
	 * 2D array that holds the location instances of each card/agent
	 */
	private Location[][] locations;
	/*
	 * String to hold the source for the file
	 */
	private String file;
	/*
	 * List to hold the 25 randomly generated codenames for the current game.
	 */
	private List<String> codenames;
	/*
	 * Int to keep count of locations that are not visible yet
	 */
	private int cardCount;
	
	
	
	/*
	 * Constructor to create the board, set up count, assign the fileName, and call
	 * the method that generates random codename and agent assignments to locations
	 */
	
	public Board() {
		locations = new Location[5][5];
		cardCount = 25;
		setFile("testfiles/codenames.txt");
		assignLocations();
	}

	/**
	 * This method reads through a file of code names and adds them all to an
	 * ArrayList of type String, which is then returned.
	 * 
	 * @param none
	 * @return ArrayList containing all the code names in a file as Strings
	 */
	public List<String> readCodeNamesFromFile() {
		List<String> codenames = new ArrayList<String>();
		try {
			for (String line : Files.readAllLines(Paths.get(file))) {
				codenames.add(line);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return codenames;
	}

	/**
	 * This method shuffles the ArrayList returned by readCodeNamesFromFile selects
	 * the first 25 code names from that ArrayList and returns a new ArrayList
	 * containing the selected code names.
	 * 
	 * @param none
	 * @return ArrayList containing 25 random code names as Strings
	 */
	public List<String> selectRandomCodeNames() {
		List<String> newCodenames = new ArrayList<String>();
		List<String> codenames = readCodeNamesFromFile();
		Collections.shuffle(codenames);
		for (int i = 0; i < 25; i++) {
			newCodenames.add(codenames.get(i));
		}
		return newCodenames;
	}

	/**
	 * This method creates an ArrayList of integers that represent agent types,
	 * shuffles them, and returns the shuffled ArrayList.
	 * 
	 * 0 = Blue agents 1 = Red agents 2 = Assassin 3 = Bystanders
	 * 
	 * @param none
	 * @return shuffled ArrayList of Integer representing agent types
	 */
	public List<Integer> createAgentTypeList() {
		List<Integer> agentTypes = new ArrayList<Integer>();
		Integer[] otherList = new Integer[] { 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 3, 3, 3, 3, 3, 3,
				3 };
		agentTypes.addAll(Arrays.asList(otherList));
		Collections.shuffle(agentTypes);
		return agentTypes;
	}
	/**
	 * This method creates an ArrayList of integers that represent agent types,
	 * shuffles them, and returns the shuffled ArrayList.
	 * 
	 * 0 = Blue agents 1 = Red agents 2 = Assassin 3 = Bystanders 4 = Green agents
	 * 
	 * @param none
	 * @return shuffled ArrayList of Integer representing agent types with 3 Teams
	 */
	public List<Integer> createAgentThreeTypeList() {
		List<Integer> agentTypes = new ArrayList<Integer>();
		Integer[] otherList = new Integer[] { 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4,
				4 };
		agentTypes.addAll(Arrays.asList(otherList));
		Collections.shuffle(agentTypes);
		return agentTypes;
	}
	/**
	 * This method creates a new ArrayList of type Person by instantiating 25 new
	 * Person instances and passing their appropriate parameters from the
	 * selectRandomCodeNames and createAgentTypeList methods.
	 * 
	 * If the game is started in 3-Team mode, method will create new ArrayList<Person>
	 * with 25 Person instances with the correct parameters to allow a 3-Team game
	 * from selectRandmCodenames and createThreeAgentTypeList methods.
	 * 
	 * @param none
	 * @return ArrayList of type Person that holds all Person assignments
	 */
	public List<Person> assignPerson() {
		codenames = selectRandomCodeNames();
		List<Person> assignments = new ArrayList<Person>();
		List<Integer> agentTypes = createAgentTypeList();
		List<Integer> threeAgentTypes = createAgentThreeTypeList();
		// create an if based on game type to call one or the other method
		if (Game.isTwoTeamGame() == false) {
			for (int i = 0; i < 25; i++) {
				assignments.add(new Person(codenames.get(i), threeAgentTypes.get(i)));
			}
		} else {
			for (int i = 0; i < 25; i++) {
				assignments.add(new Person(codenames.get(i), agentTypes.get(i)));
			}
		}
		return assignments;
	}

	/**
	 * This method creates a new 2D array of type Location and fills the array with
	 * new Location instances that are created by cycling over an ArrayList of type
	 * Person and assigning (X,Y) coordinates that coincide with the instance's
	 * position in the 2D array.
	 * 
	 * @param none
	 * @return none
	 */
	public void assignLocations() {
		locations = new Location[5][5];
		List<Person> people = assignPerson();
		int indx = 0;
		for (int i = 0; i < locations.length; i++) {
			for (int j = 0; j < locations[0].length; j++) {
				locations[i][j] = new Location(i, j, people.get(indx));
				indx++;
			}
		}
	}

	/**
	 * Determines if a clue given by the spymaster is legal or not It is legal if
	 * and only it is NOT the codename of an unrevealed agent. However if the agent
	 * is already revealed, their codename could be a legal clue
	 * 
	 * @param String
	 *            which is the clue given by the spymaster
	 * @return {@code true} if the clue is legal or {@code false} if it is not
	 */
	public boolean legalClue(String clue) {
		if (clue == null)
			throw new NullPointerException();
		if (clue == "" || clue.contains(" "))
			return false;
		for(int i=0;i<10;i++){
			if(clue.contains(i+"")){
				return false;
			}
		}
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				Location loc = locations[i][j];
				if (!loc.isVisible() && clue.equals(loc.getPerson().getAgentName().toLowerCase()))
					return false;
			}
		}
		return true;
	}

	/**
	 * This method determines whether the game is in a winning state, A winning
	 * state is when: - either all 9 of the red team's agents are revealed, or all 8
	 * of the blue teams agents are revealed - or when an assassin is revealed and
	 * the game ends.
	 * 
	 * The method is split up into two methods that return booleans for both testing
	 * and readability purposes
	 * 
	 * @param none
	 * @return {@code true} if the board is in a winning state {@code false}
	 *         otherwise
	 */
	public boolean checkWinningState() {
		if(Game.isTwoTeamGame())
			return allAgentsRevealed() || checkForAssassin();
		else
			return checkForAssassinThree() || allAgentsRevealedThree();
			
	}
	private String assassinWin(){
		if(checkForAssassinThree())
			return Game.getWinner();
		return "";
	}
	private boolean checkForAssassinThree() {
		
		// TODO Auto-generated method stub
		int assassinRevealedCount = 0;
		for (int i = 0; i < locations.length; i++) {
			for (int j = 0; j < locations[0].length; j++) {
				Location loc = locations[i][j];
				if (loc.getPerson().getAgentType() == 2 && loc.isVisible())
					assassinRevealedCount++;
			}
		}
		if (assassinRevealedCount==2) {
			if (Game.isBlueAssassin()&&Game.isRedAssassin()) {
				Game.setWinner("Green");
				return true;
			} else if(Game.isRedAssassin()&&Game.isGreenAssassin()){
				Game.setWinner("Blue");
				return true;
			}else if(Game.isGreenAssassin()&&Game.isBlueAssassin()){
				Game.setWinner("Red");
				return true;
			}

		}
		return false;


	}

	private boolean allAgentsRevealedThree() {
		int countRed = 0;
		int countBlue = 0;
		int countGreen = 0;
		// tracks how many agents revealed
		for (int r = 0; r < 5; r++) {
			for (int c = 0; c < 5; c++) {
				Location loc = locations[r][c];
				if (loc.isVisible() && loc.getPerson().getAgentType() == 0)
					countBlue++;
				else if (loc.isVisible() && loc.getPerson().getAgentType() == 1)
					countRed++;
				else if (loc.isVisible() && loc.getPerson().getAgentType() == 4)
					countGreen++;
			}
		}

		if (countRed == 6) {
			Game.setWinner("Red");
			return true;
		}

		if (countBlue == 5) {
			Game.setWinner("Blue");
			return true;
		}
		if (countGreen == 5) {
			Game.setWinner("Green");
			return true;
		}

		return false;
	}

	/**
	 * 
	 * Determined if a team Reveals the Assassin, then that team is immediately
	 * "killed". The game is over and the other team is declared the winner.
	 * 
	 * 
	 * @param none
	 * @return {@code true} if one of the locations is revealed and the agent is an
	 *         assassin {@code false} otherwise
	 */
	public boolean checkForAssassin() {

		boolean assassinRevealed = false;
		for (int i = 0; i < locations.length; i++) {
			for (int j = 0; j < locations[0].length; j++) {
				Location loc = locations[i][j];
				if (loc.getPerson().getAgentType() == 2 && loc.isVisible())
					assassinRevealed = true;
			}
		}
		if (assassinRevealed) {
			if (Game.getTurn() == 1) {
				Game.setWinner("Blue");
				return true;
			} else {
				Game.setWinner("Red");
				return true;
			}

		}
		return false;
	}

	/**
	 * Determines if either team revealed all of their agents If all 9 Red Agents
	 * are Revealed, the Red team wins. This is true even if it is a Blue team
	 * selection that Revealed the final Red Agent. If all 8 Blue Agents are
	 * Revealed, the Blue team wins. This is true even if it is a Red team selection
	 * that Revealed the final Blue Agent.
	 * 
	 * 
	 * @param none
	 * @return {@code true} if all agents of either team are revealed
	 */
	public boolean allAgentsRevealed() {
		int countRed = 0;
		int countBlue = 0;
		// tracks how many agents revealed
		for (int r = 0; r < 5; r++) {
			for (int c = 0; c < 5; c++) {
				Location loc = locations[r][c];
				if (loc.isVisible() && loc.getPerson().getAgentType() == 0)
					countBlue++;
				else if (loc.isVisible() && loc.getPerson().getAgentType() == 1)
					countRed++;
			}
		}

		if (countRed == 9) {
			Game.setWinner("Red");
			return true;
		}

		if (countBlue == 8) {
			Game.setWinner("Blue");
			return true;
		}

		return false;
	}

	/**
	 * 
	 * When a codeName is selected and if it matches an agent that is unrevealed the
	 * following occur: - cardCound is decremented - Location is updated to be
	 * visible - returns a boolean which states if the agent revealed matches if the
	 * codeName doesn't match any of the agents who are unrevealed (ie the agent
	 * with that codename was already revealed) nothing happens to count and
	 * location and the method returns false.
	 *
	 * @param String
	 *            that is the codeName of an agent who is set to be revealed.
	 * @return {@code true} if the agent who's codename is revealed is an agent of
	 *         the team who's turn it is else {@code false} if the agent revealed
	 *         isn't the color of the team who revelaed them or if the codeName
	 *         doesn't match any of the agents that are still unrevealed.
	 */
	public boolean selectCodeName(String codeName) {
		boolean assassin = false;
		int personRevealed = 5;
		if (codeName == null)
			throw new NullPointerException();

		for (int r = 0; r < 5; r++) {
			for (int c = 0; c < 5; c++) {
				Location loc = locations[r][c];
				if (codeName.equals(loc.getPerson().getAgentName())) {
					cardCount--;
					loc.setVisible(true);
					personRevealed = loc.getPerson().getAgentType();
					if(personRevealed == 2)
						Game.revealedAssassin(Game.getTurn());
				}
			}
		}

		if (personRevealed == Game.getTurn())
			return true;
		else{
			Game.switchTeamTurn();
			Game.changeControl();
			return false;
		}

	}

	/**
	 * 
	 * @return a List of codeNames used for the 25 locations
	 */
	public List<String> getCodenamesUsed() {
		return codenames;
	}

	/**
	 * 
	 * @return the number of locations/cards that have not yet been revealed
	 */
	public int getCount() {
		return cardCount;
	}

	/**
	 * 
	 * 
	 * @return the String that is the src of a file used to pick random codenames
	 */
	public String getFile() {
		return file;
	}

	/**
	 * 
	 * @param String
	 *            that is the source of the file that holds all the possible
	 *            codenames
	 */
	public void setFile(String filename) {
		file = filename;
	}

	/**
	 * 
	 * @return 2d array of Location instances, that store the agents
	 */
	
	public Location[][] getLocations() {
		return locations;
	}

	public boolean legalCount(int countForTurn) {
		if(countForTurn<=cardCount && countForTurn>0){
			return true;
		}
		
		return false;
	}
	
	

}
