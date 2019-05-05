package application.model.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import application.model.beans.Monster;

public class MonsterDAO extends DAO<Monster>{	
	
	public ArrayList<Monster> findAll(){
		ArrayList<Monster> monsterList =new ArrayList<Monster>();
		try {
			Connection connection = MySqlConnection.startConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM uldvelh.monster ORDER BY name");
			
			while(result.next()) {
				monsterList.add(new Monster(result.getInt("idMonster"),result.getString("name"),result.getInt("health"),result.getInt("damage"),result.getInt("rank")));
			}
			MySqlConnection.endConnection();
			return monsterList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void addMonster(String name,int health,int damage,int rank) {
		try {
			Connection connection = MySqlConnection.startConnection();
			Statement statement = connection.createStatement();
			statement.execute("INSERT INTO uldvelh.monster(name,health,damage,rank) VALUES ('"+name+"',"+health+","+damage+","+rank+")");
			MySqlConnection.endConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteById(int id) {
		try {
			Connection connection = MySqlConnection.startConnection();
			Statement statement = connection.createStatement();
			statement.execute("DELETE FROM uldvelh.monster WHERE idMonster="+id);
			MySqlConnection.endConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateMonster(Monster m) {
		try {
			Connection connection = MySqlConnection.startConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE uldvelh.monster SET health="+m.getHealth()+",damage="+m.getDamage()+",rank="+m.getRank()+" WHERE idMonster="+m.getIdMonster());
			MySqlConnection.endConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public Monster findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Monster create(Monster obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Monster update(Monster obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Monster obj) {
		// TODO Auto-generated method stub
		
	}

}
