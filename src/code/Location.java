package code;

public class Location {

	// @author  Kateryna Semenova
	private String agentName;
	private int agentType; //0 Blue, 1 Red, 2 Assasin, 4 Bystander
	private int xCoord;
	private int yCoord;
	private boolean visible;
	
	public Location(int x, int y, String codename, int agentType){
		setxCoord(x);
		setyCoord(y);
		agentName = codename;
		this.agentType = agentType;
		visible = false;
	}

	public String getAgentName() {
		return agentName;
	}

	public int getAgentType() {
		return agentType;
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
	
	
}
