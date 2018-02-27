package code;

public class Person {
	
	//@author Hollis Pauquette
	private String agentName;
	private Integer agentType;

	public Person(String codename, Integer agentType) {
		setAgentName(codename);
		this.agentType = agentType;
		//setAgentType(agentType); // "Red" = 1, "Blue" = 0, "Bystander" = 3, "Assassin" = 2
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public Integer getAgentType() {
		return agentType;
	}

}
