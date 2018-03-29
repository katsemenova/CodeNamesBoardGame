package tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import code.Board;
import code.Location;

//@author Kateryna Semenova
//@author Sidney Bloch

public class BoardTest {

	private Board b = new Board();

	@Test
	public void createBoardTest() {
		Location[][] arr = b.getLocations();
		assertEquals("The board is not 25 locations", 25, arr.length * arr[0].length);

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				Object loc = arr[i][j];
				boolean value = (loc instanceof Location);
				assertEquals("The location is undefined", true, value);
				assertEquals("The location is undefined", true, loc != null);
			}
		}
	}

	@Test
	public void readCodeNamesFromFileTest() {
		b.setFile("./testfiles/codenames.txt");
		ArrayList<String> code = new ArrayList<String>(b.readCodeNamesFromFile());
		ArrayList<String> act = new ArrayList<String>();
		Collections.addAll(act, "area", "book", "business", "case", "child", "company", "country", "day", "eye", "fact",
				"family", "government", "group", "hand", "home", "job", "life", "lot", "man", "money", "month",
				"mother", "Mr", "night", "number", "part", "people", "place", "point", "problem", "program", "question",
				"right", "room", "school", "state", "story", "student", "study", "system", "thing", "time", "water",
				"way", "week", "woman", "word", "work", "world", "year");
		
		assertEquals("Check your Read from File method", act, code);
	}

	@Test
	public void distinctCodeNames() {
		Board n = new Board();
		n.setFile("./testfiles/codenames.txt");
		ArrayList<String> act = new ArrayList<String>(n.selectRandomCodeNames());
		ArrayList<String> codeNames = new ArrayList<String>(b.selectRandomCodeNames());
		assertEquals("Somewhere is your randomizer you screwed up", 25, codeNames.size());
		assertFalse("Check your randomizer, Im not sure if it is functioning properly.", act.equals(codeNames));
	}

	@Test
	public void createAgentTypeListTest() {
		int iB = 0;
		int bl = 0;
		int r = 0;
		int a = 0;
		ArrayList<Integer> agents = new ArrayList<Integer>(b.createAgentTypeList());
		for (int i = 0; i < agents.size(); i++) {
			int p = agents.get(i);
			if (p == 0) {
				bl++;
			} else if (p == 1) {
				r++;
			} else if (p == 2) {
				a++;
			} else {
				iB++;
			}
		}
		assertEquals("Check your Innocent Bystanders", 7, iB);
		assertEquals("Check your Red Team Comp", 8, bl);
		assertEquals("Check your Blue Team Comp", 9, r);
		assertEquals("Check your Assasin assignment", 1, a);
	}
}
