package code;

public class Location {

	// @author  Kateryna Semenova
	/*
	 * 
	 */
	private Person person;
	/*
	 * 
	 */
	private int xCoord;
	/*
	 * 
	 */
	private int yCoord;
	/*
	 * 
	 */
	private boolean visible;
	/*
	 * 
	 */
	private String codeName;
	
	/**
	   * 
	   * 
	   * @param 
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
	   * 
	   * @param 
	   * @return 
	   */
	public String getLocationCodename(){
		return codeName;
	}
	/**
	   * 
	   * 
	   * @param 
	   * @return 
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
	   * 
	   * @param 
	   * @return 
	   */
	public int getxCoord() {
		return xCoord;
	}
	/**
	   * 
	   * 
	   * @param 
	   * @return 
	   */
	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}
	/**
	   * 
	   * 
	   * @param 
	   * @return 
	   */
	public int getyCoord() {
		return yCoord;
	}
	/**
	   * 
	   * 
	   * @param 
	   * @return 
	   */
	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	/**
	   * 
	   * 
	   * @param 
	   * @return 
	   */
	public Person getPerson() {
		return this.person;
	}
	
}
