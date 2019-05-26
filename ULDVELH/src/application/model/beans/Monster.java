package application.model.beans;

public class Monster {
	private int idMonster;
	private String name;
	private int health;
	private int damage;
	private int rank;
	
	public Monster(int id,String name,int health,int damage,int rank) {
		setIdMonster(id);
		setName(name);
		setHealth(health);
		setDamage(damage);
		setRank(rank);
	}
	

	public int getIdMonster() {
		return idMonster;
	}
	public void setIdMonster(int idMonster) {
		this.idMonster = idMonster;
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
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
}
