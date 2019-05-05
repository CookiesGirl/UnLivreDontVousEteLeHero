package application.model.beans;

public class Character {
	private int id;
	private String name;
	private int health;
	private int currentChapter;
	private Bag bag;
	private Equipment equipment;
	
	public Character(int id,String name,int health,int currentChapter) {
		this.id=id;
		this.name=name;
		this.health=health;
		this.currentChapter=currentChapter;
	}

	
	
	
	
	
	
	
	
	
	
	
	public int getCurrentChapter() {
		return currentChapter;
	}
	public void setCurrentChapter(int currentChapter) {
		this.currentChapter = currentChapter;
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
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public Bag getBag() {
		return bag;
	}
	public void setBag(Bag bag) {
		this.bag = bag;
	}
	public Equipment getEquipment() {
		return equipment;
	}
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}
	
}
