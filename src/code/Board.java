package code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
	
	private Location[][] locations;
	
	private String file;

	public Board() {
		locations = new Location[5][5];
	}
	
	public String getFile() {
		return file;
	}
	
	public void setFile(String filename) {
		file = filename;
	}
	
	public List<String> readCodeNamesFromFile() {
		List<String> codenames = new ArrayList<String>();
		try {
	        for(String line : Files.readAllLines(Paths.get(file))){
	            codenames.add(line);
	        }
	    }
		catch (IOException ex){
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
	}
	
	public List<Person> assignPerson() {
		List<String> codenames = selectRandomCodeNames();
		List<Person> assignments = new ArrayList<Person>();
		for (String name : codenames) {
			assignments.add(new Person(name, ));
		}
	}

}
