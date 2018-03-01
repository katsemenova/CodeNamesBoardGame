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
	 * Constructor to create the board, set up count, assign the fileName, and call the method that generates random 
	 * codename and agent assignments to locations
	 */
	public Board() {
		locations = new Location[5][5];
		cardCount = 25;
		setFile("testfiles/codenames.txt");
		assignLocations();
	}
	

	/**
	   * 
	   * 
	   * @param 
	   * @return 
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
	   * 
	   * 
	   * @param 
	   * @return 
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
	   * 
	   * 
	   * @param 
	   * @return 
	   */
	public List<Integer> createAgentTypeList() {
		List<Integer> agentTypes = new ArrayList<Integer>();
		Integer[] otherList = new Integer[] {0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,2,3,3,3,3,3,3,3};
		agentTypes.addAll(Arrays.asList(otherList));
		Collections.shuffle(agentTypes);
		return agentTypes;
	}
	/**
	   * 
	   * 
	   * @param 
	   * @return 
	   */
	public List<Person> assignPerson() {
		List<Integer> agentTypes = createAgentTypeList();
		codenames = selectRandomCodeNames();
		List<Person> assignments = new ArrayList<Person>();
		for (int i = 0; i < 25; i++) {	
			assignments.add(new Person(codenames.get(i), agentTypes.get(i)));
		}
		return assignments;
	}
	/**
	   * 
	   * 
	   * @param 
	   * @return 
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
	   * Determines if a clue given by the spymaster is legal or not
	   * It is legal if and only it is NOT the codename of an unrevealed agent. 
	   * However if the agent is already revealed, their codename could be a legal clue 
	   * 
	   * @param String which is the clue given by the spymaster
	   * @return {@code true} if the clue is legal or {@code false} if it is not
	   */
	public boolean legalClue(String clue) {
		if(clue == null)
			throw new NullPointerException();
		if(clue == "")
			return false;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				Location loc = locations[i][j];
				if (!loc.isVisible() && clue.equals(loc.getPerson().getAgentName()))
					return false;
			}
		}
		return true;
	}
	
	/**
	   * 
	   * 
	   * @param 
	   * @return 
	   */
	public boolean checkWinningState() {
		return allAgentsRevealed() || checkForAssassin();
	}
	/**
	   * 
	   * 
	   * @param 
	   * @return 
	   */
	public boolean checkForAssassin() {
				
		boolean assassinRevealed = false;
		for(int i = 0;i<locations.length;i++){
			for(int j = 0; j <locations[0].length;j++){
				Location loc = locations[i][j];
				if(loc.getPerson().getAgentType() == 2 && loc.isVisible())
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
	   * 
	   * 
	   * @param 
	   * @return 
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
	   * 
	   * @param 
	   * @return 
	   */
	public boolean selectCodeName(String codeName) {
		cardCount--;
		int personRevealed = 5;
		if(codeName == null)
			throw new NullPointerException();
		
		for (int r = 0; r < 5; r++) {
			for (int c = 0; c < 5; c++) {
				Location loc = locations[r][c];
				if (codeName.equals(loc.getPerson().getAgentName())) {
					loc.setVisible(true);
					personRevealed = loc.getPerson().getAgentType();
				}
			}
		}

		if (personRevealed == Game.getTurn())
			return true;
		return false;

	}
	/**
	   * 
	   * 
	   * @param 
	   * @return 
	   */
	public List<String> getCodenamesUsed() {
		return codenames;
	}
	
	/**
	   * 
	   * 
	   * @param 
	   * @return 
	   */
	public int getCount(){
		return cardCount;
	}
	/**
	   * 
	   * 
	   * @param 
	   * @return 
	   */
	public String getFile() {
		return file;
	}
	/**
	   * 
	   * 
	   * @param 
	   * @return 
	   */
	public void setFile(String filename) {
		file = filename;
	}
	/**
	   * 
	   * 
	   * @param 
	   * @return 
	   */
	public Location[][] getLocations() {
		return locations;
	}
}
