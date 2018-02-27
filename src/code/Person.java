package code;

public class Person {
	
	//@author Hollis Pauquette
	private String agentName;
	private Integer agentType;

	public Person(String codename, Integer agentType) {
		setAgentName(codename);
		setAgentType(agentType); // "Red", "Blue", "Bystander", "Assassin"
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

	public void setAgentType(Integer agentType) {
		this.agentType = agentType;
	}

}
