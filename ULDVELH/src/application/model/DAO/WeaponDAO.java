package application.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import application.model.beans.Weapon;

public class WeaponDAO extends DAO<Weapon>{

	@Override
	public Weapon findById(int id) {
		Weapon w =null;
		try {
			Connection connection = MySqlConnection.startConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM uldvelh.weapon WHERE id= ?");
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				w = new Weapon(result.getInt("idWeapon"),result.getString("name"),result.getInt("damage"));
			}
			result.close();
			statement.close();
			return w;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<Weapon> findAll() {
		ArrayList<Weapon> w =new ArrayList<Weapon>();
		try {
			Connection connection = MySqlConnection.startConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM uldvelh.weapon ORDER BY name");
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				w.add(new Weapon(result.getInt("idWeapon"),result.getString("name"),result.getInt("damage")));
			}
			result.close();
			statement.close();
			return w;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void create(Weapon w) {
		try {
			Connection connection = MySqlConnection.startConnection();
			PreparedStatement statement = connection.prepareStatement("INSERT INTO uldvelh.weapon(name,damage) VALUES (?,?)");
			statement.setString(1, w.getName());
			statement.setInt(2, w.getDamage());
			statement.execute();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Weapon w) {
		try {
			Connection connection = MySqlConnection.startConnection();
			PreparedStatement statement = connection.prepareStatement("UPDATE uldvelh.weapon SET name=?, damage=? WHERE idWeapon= ?");
			statement.setString(1, w.getName());
			statement.setInt(2, w.getDamage());
			statement.setInt(3, w.getId());
			statement.execute();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Weapon w) {
		try {
			Connection connection = MySqlConnection.startConnection();
			PreparedStatement statement = connection.prepareStatement("DELETE FROM uldvelh.weapon WHERE idWeapon= ?");
			statement.setInt(1, w.getId());
			statement.execute();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
