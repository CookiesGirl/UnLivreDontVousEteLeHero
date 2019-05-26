package application.model.beans;

import java.util.ArrayList;

public class Chapter {
	private int id;
	private String text;
	private ArrayList<Choice> choices=null;
	
	
	public Chapter(int id,String text) {
		this.id=id;
		this.text=text;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getText() {
		return text;
	}
	public ArrayList<Choice> getChoices() {
		return choices;
	}
	public void setChoices(ArrayList<Choice> choices) {
		this.choices=choices;
		for(Choice c :choices) {
			c.setChapter(this.id);
		}
	}
}
