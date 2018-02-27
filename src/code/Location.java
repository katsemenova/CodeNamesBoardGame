package code;

public class Location {

	// @author  Kateryna Semenova
	private Person person;
	private int xCoord;
	private int yCoord;
	private boolean visible;
	private String codeName;
	
	public Location(int x, int y, Person person){
		this.person = person;
		setxCoord(x);
		setyCoord(y);
		visible = false;
		codeName = person.getAgentName();
		
	}
	public String getLocationCodename(){
		return codeName;
	}
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public int getxCoord() {
		return xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	
	public Person getPerson() {
		return this.person;
	}
	
}
