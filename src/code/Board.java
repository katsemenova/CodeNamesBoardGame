package code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Instances of this class represent the game board in it's current state
 * 
 * @author Hollis Pauquette
 * @author Kateryna Semenova
 */

public class Board {

	private Location[][] locations;
	private String file;
	private List<String> codenames;
	private int cardCount;

	public Board() {
		locations = new Location[5][5];
		cardCount = 25;
		setFile("testfiles/codenames.txt");
		assignLocations();
	}
	
	
	public int getCount(){
		return cardCount;
	}
	public String getFile() {
		return file;
	}

	public void setFile(String filename) {
		file = filename;
	}
	
	public Location[][] getLocations() {
		return locations;
	}

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

	public List<String> selectRandomCodeNames() {
		List<String> newCodenames = new ArrayList<String>();
		List<String> codenames = readCodeNamesFromFile();
		Collections.shuffle(codenames);
		for (int i = 0; i < 25; i++) {
			newCodenames.add(codenames.get(i));
		}
		return newCodenames;
	}

	public List<Integer> createAgentTypeList() {
		List<Integer> agentTypes = new ArrayList<Integer>();
		agentTypes.add(0);
		agentTypes.add(0);
		agentTypes.add(0);
		agentTypes.add(0);
		agentTypes.add(0);
		agentTypes.add(0);
		agentTypes.add(0);
		agentTypes.add(0);
		agentTypes.add(1);
		agentTypes.add(1);
		agentTypes.add(1);
		agentTypes.add(1);
		agentTypes.add(1);
		agentTypes.add(1);
		agentTypes.add(1);
		agentTypes.add(1);
		agentTypes.add(1);
		agentTypes.add(2);
		agentTypes.add(3);
		agentTypes.add(3);
		agentTypes.add(3);
		agentTypes.add(3);
		agentTypes.add(3);
		agentTypes.add(3);
		agentTypes.add(3);
		return agentTypes;
	}

	public List<Person> assignPerson() {
		List<Integer> agentTypes = createAgentTypeList();
		codenames = selectRandomCodeNames();
		List<Person> assignments = new ArrayList<Person>();
		for (int i = 0; i < 25; i++) {	
			assignments.add(new Person(codenames.get(i), agentTypes.get(i)));
		}
		return assignments;
	}
	
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

	// game rule
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

	public boolean checkWinningState() {
		return allAgentsRevealed() || checkForAssassin();
	}
	public boolean checkForAssassin() {
				// checks for assassin
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

	public List<String> getCodenamesUsed() {
		return codenames;
	}
	
	
}
