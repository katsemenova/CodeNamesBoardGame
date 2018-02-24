package code;

public class Person {
	
	private String agentName;
	private int agentType;

	public Person(String codename, int agentType) {
		setAgentName(codename);
		setAgentType(agentType);
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
