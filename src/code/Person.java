package code;

public class Person {
	
	//@author Hollis Pauquette
	/*
	 * 
	 */
	private String agentName;
	/*
	 * 
	 */
	private Integer agentType;
	/**
	   * 
	   * 
	   * @param 
	   * @return 
	   */
	public Person(String codename, Integer agentType) {
		setAgentName(codename);
		this.agentType = agentType;
		//setAgentType(agentType); // "Red" = 1, "Blue" = 0, "Bystander" = 3, "Assassin" = 2
	}
	/**
	   * 
	   * 
	   * @param 
	   * @return 
	   */
	public String getAgentName() {
		return agentName;
	}
	/**
	   * 
	   * 
	   * @param 
	   * @return 
	   */
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	/**
	   * 
	   * 
	   * @param 
	   * @return 
	   */
	public Integer getAgentType() {
		return agentType;
	}

}
