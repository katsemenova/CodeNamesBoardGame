package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import code.Board;
import code.Location;

public class AgentsTest {
	
	//First, if person has int value from 0-3 and has string value
	//Second, make location instance with params. Ensure assigned correctly.
	//Codename, person, boolean cannot be null
	//Third, make instance of Board, keep track of number of red, blue, assassins, bystanders 
	
	// @Author Hollis Pauquette, Aaron Kong
	
	@Test
	public void testPersonsForNull() { 
		Board b = new Board();
//		b.setFile("testfiles/codenames.txt");
//		b.assignLocations();
		Location[][] theLocations = b.getLocations();
		for (int i = 0; i < theLocations.length; i++) {
			for (int j = 0; j < theLocations[0].length; j++) {
				assertTrue(theLocations[i][j].getPerson().getAgentName() != null);
				assertTrue(theLocations[i][j].getPerson().getAgentType() != null && theLocations[i][j].getPerson().getAgentType() <= 3);
				assertTrue(theLocations[i][j].getPerson().getAgentType() != null && theLocations[i][j].getPerson().getAgentType() >= 0);
				assertTrue(theLocations[i][j] != null);
			}
		}
		
	}
	
	@Test
	public void testRedAgents() {
		int rCount = 0;
		Board b = new Board();
//		b.setFile("testfiles/codenames.txt");
//		b.assignLocations();
		Location[][] theLocations = b.getLocations();
		for (int i = 0; i < theLocations.length; i++) {
			for (int j = 0; j < theLocations[0].length; j++) {
				if(theLocations[i][j].getPerson().getAgentType() == 1) {
					rCount += 1;
				}
				
			}
		}
		assertEquals(9,rCount);
	}
	
	@Test
	public void testBlueAgents() {
		int bCount = 0;
		Board b = new Board();
//		b.setFile("testfiles/codenames.txt");
//		b.assignLocations();
		Location[][] theLocations = b.getLocations();
		for (int i = 0; i < theLocations.length; i++) {
			for (int j = 0; j < theLocations[0].length; j++) {
				if(theLocations[i][j].getPerson().getAgentType() == 0) {
					bCount += 1;
				}
				
			}
		}
		assertEquals(8,bCount);
	}
	
	@Test
	public void testAssassins() {
		int aCount = 0;
		Board b = new Board();
//		b.setFile("testfiles/codenames.txt");
//		b.assignLocations();
		Location[][] theLocations = b.getLocations();
		for (int i = 0; i < theLocations.length; i++) {
			for (int j = 0; j < theLocations[0].length; j++) {
				if(theLocations[i][j].getPerson().getAgentType() == 2) {
					aCount += 1;
				}
				
			}
		}
		assertEquals(1,aCount);
	}
	
	@Test
	public void testBystanders() {
		int byCount = 0;
		Board b = new Board();
//		b.setFile("testfiles/codenames.txt");
//		b.assignLocations();
		Location[][] theLocations = b.getLocations();
		for (int i = 0; i < theLocations.length; i++) {
			for (int j = 0; j < theLocations[0].length; j++) {
				if(theLocations[i][j].getPerson().getAgentType() == 3) {
					byCount += 1;
				}
				
			}
		}
		assertEquals(7,byCount);
	}

	}
