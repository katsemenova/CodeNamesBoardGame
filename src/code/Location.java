package code;

public class Location {

	// @author  Kateryna Semenova
	// @authod Hollis Pauquette 
	/*
	 * Instance of person that is the agent of this location
	 */
	private Person person;
	/*
	 * x coordinate of this location as it stands in the 2d Array
	 */
	private int xCoord;
	/*
	 * y coordinate of this location as it stands in the 2d Array 
	 */
	private int yCoord;
	/*
	 * boolean that determines if this location has been revealed or not. It is revealed if it's codename has been chosen by either team
	 */
	private boolean visible;
	/*
	 * The codename set for this location, also codeName of the Person class instance. 
	 */
	private String codeName;
	
	/**
	   * The constructor of location, sets up the field values when a Location is instantiated 
	   * 
	   * @param x the x- coordinate of this location 
	   * @param y the y-coordinate of this location
	   * @param person assigns the agent/bystander/assassin to this class
	   * @return 
	   */
	public Location(int x, int y, Person person){
		this.person = person;
		setxCoord(x);
		setyCoord(y);
		visible = false;
		codeName = person.getAgentName();
		
	}
	/**
	   * 
	   * @return the codeName of this location instance
	   */
	public String getLocationCodename(){
		return codeName;
	}
	/**
	   * 
	   * @return {@code true} if this Location instance is "visible" and hence revealed by either team {@code false} otherwise
	   */
	public boolean isVisible() {
		return visible;
	}
	/**
	   * 
	   * 
	   * @param 
	   * @return 
	   */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	/**
	   * 
	   * @return an int that is the x-coordinate of this location instance in a 2d board array
	   */
	public int getxCoord() {
		return xCoord;
	}
	/**
	   * 
	   * 
	   * @param  xCoord that sets the x-coordinate location of this location in a 2d board array
	   */
	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}
	/**
	   * 

	   * @return an int that is the y-coordinate of this location instance in a 2d board array
	   */
	public int getyCoord() {
		return yCoord;
	}
	/**
	   * 
	   * 
	   * @param yCoord that sets the y-coordinate location of this location in a 2d  board array 
	   */
	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	/**
	   * 

	   * @return Person instance of this class, ie: agent/ bystander/ assasin of this class
	   */
	public Person getPerson() {
		return this.person;
	}
	
}
