package application;

public class Choice {
	
	public int index;
	
	private String text;
	
	private int destination;
	
	public Choice(String text, int destination, int index) {
		this.text=text;
		this.destination=destination;
		this.index=index;
	}
	
	public String getText() {
		return this.text;
	}
	
	public int getDestination() {
		return this.destination;
	}

}
