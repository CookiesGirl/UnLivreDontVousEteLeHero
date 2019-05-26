package application.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import application.model.beans.Monster;

public class MonsterDAO extends DAO<Monster>{	
	
	public ArrayList<Monster> findAll(){
		ArrayList<Monster> monsterList =new ArrayList<Monster>();
		try {
			Connection connection = MySqlConnection.startConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM uldvelh.monster ORDER BY name");
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				monsterList.add(new Monster(result.getInt("idMonster"),result.getString("name"),result.getInt("health"),result.getInt("damage"),result.getInt("rank")));
			}
			result.close();
			statement.close();
			return monsterList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	@Override
	public Monster findById(int id) {
		Monster monster= null;
		try {
			Connection connection = MySqlConnection.startConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM uldvelh.monster ORDER BY name");
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				monster= new Monster(result.getInt("idMonster"),result.getString("name"),result.getInt("health"),result.getInt("damage"),result.getInt("rank"));
			}
			result.close();
			statement.close();
			return monster;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void create(Monster m) {
		try {
			Connection connection = MySqlConnection.startConnection();
			PreparedStatement statement = connection.prepareStatement("INSERT INTO uldvelh.monster(name,health,damage,rank) VALUES ( ? , ? , ? , ? )");
			statement.setString(1, m.getName());
			statement.setInt(2, m.getHealth());
			statement.setInt(3, m.getDamage());
			statement.setInt(4, m.getRank());
			statement.execute();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void delete(Monster m) {
		try {
			Connection connection = MySqlConnection.startConnection();
			PreparedStatement statement = connection.prepareStatement("DELETE FROM uldvelh.monster WHERE idMonster=?");
			statement.setInt(1, m.getIdMonster());
			statement.execute();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(Monster m) {
		try {
			Connection connection = MySqlConnection.startConnection();
			PreparedStatement statement = connection.prepareStatement("UPDATE uldvelh.monster SET health= ? ,damage= ? ,rank= ? WHERE idMonster= ?");
			statement.setInt(1, m.getHealth());
			statement.setInt(2, m.getDamage());
			statement.setInt(3, m.getRank());
			statement.setInt(4, m.getIdMonster());
			statement.executeUpdate();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
