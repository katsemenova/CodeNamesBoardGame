package code;

public class Person {
	
	//@author Hollis Pauquette
	private String agentName;
	private int agentType;

	public Person(String codename, int agentType) {
		setAgentName(codename);
		setAgentType(agentType); // 0 - Blue, 1 - Red, 2 - Assassin, 3 - Bystander
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public int getAgentType() {
		return agentType;
	}

	public void setAgentType(int agentType) {
		this.agentType = agentType;
	}

}
