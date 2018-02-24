package code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
		shuffle(readCodeNamesFromFile());
		return codenames;
		
	}

}
