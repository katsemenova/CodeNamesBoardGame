package code;

public class Person {
	
	//@author Hollis Pauquette
	/*
	 * 
	 */
	private String agentName;
	/*
	 * Integer that holds the int value that stands for a specific agent type
	 *  "Red" = 1, "Blue" = 0, "Bystander" = 3, "Assassin" = 2
	 */
	private Integer agentType;
	/**
	   * 
	   * @param codename the assigned codeName of this person instance
	   * @param agentType the assigned agentType of this person instance 
	   */
	public Person(String codename, Integer agentType) {
		setAgentName(codename);
		this.agentType = agentType;
		
	}
	/**
	   * 
	   * @return the String that is agents codename
	   */
	public String getAgentName() {
		return agentName;
	}
	/**
	   * 
	   * @param String that sets the codename of the agent
	   */
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	/**
	   * 
	   * @return {@code int} that depicts which type of person this is 
	   */
	public Integer getAgentType() {
		return agentType;
	}
	/*
	 * depicts the agent type in string value instead of int
	 * @return a String value of the agent
	 */
	public String getAgentTypeString(){
		if(agentType == 0)
			return "Blue";
		else if(agentType == 1)
			return "Red";
		else if(agentType == 2)
			return "Assassin";
		else 
			return "Bystander";
	}

}
