package code;

public class Location {

	// @author  Kateryna Semenova
	private String agentName;
	private int agentType; //0 Blue, 1 Red, 2 Assasin, 4 Bystander
	private boolean visible;
	
	public Location(String codename, int agentType){
		agentName = codename;
		this.agentType = agentType;
		visible = false;
	}
}
