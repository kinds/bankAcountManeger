package client;

public class Sop {
	private String name;
	

	public Sop(String name) {
		super();
		this.name = name;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	public  void out(String string)
	{
		System.out.println("["+name+"[-->"+string);
	}

}
