package application.model.beans;

public class Player {
	private int id;
	private String username;
	private int health;
	private int idChapter;
	private int privileges;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getIdChapter() {
		return idChapter;
	}
	public void setIdChapter(int idChapter) {
		this.idChapter = idChapter;
	}
	public int getPrivileges() {
		return privileges;
	}
	public void setPrivileges(int privileges) {
		this.privileges = privileges;
	}
}
