package application.model.beans;

import java.util.ArrayList;

public class Bag {
	private int id;
	private String name;
	private ArrayList<Object> objectList = new ArrayList<Object>();
	
	public Bag(int id, String name, ArrayList<Object> objectList) {
		super();
		this.id = id;
		this.name = name;
		this.objectList = objectList;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Object> getObjectList() {
		return objectList;
	}
	public void setObjectList(ArrayList<Object> objectList) {
		this.objectList = objectList;
	}

}
