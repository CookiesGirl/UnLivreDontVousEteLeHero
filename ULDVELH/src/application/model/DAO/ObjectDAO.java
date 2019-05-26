package application.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import application.model.beans.Object;

public class ObjectDAO extends DAO<Object>{

	@Override
	public Object findById(int id) {
		Object o =null;
		try {
			Connection connection = MySqlConnection.startConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM uldvelh.object WHERE id= ?");
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				o = new Object(result.getInt("id"),result.getString("name"));
			}
			result.close();
			statement.close();
			return o;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Object> findAll(){
		ArrayList<Object> objectList = new ArrayList<Object>();
		try {
			Connection connection = MySqlConnection.startConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM uldvelh.object ORDER BY name");
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				objectList.add(new Object(result.getInt("idObject"),result.getString("name")));
			}
			result.close();
			statement.close();
			return objectList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return objectList;
	}
	
	@Override
	public void create(Object o) {
		try {
			Connection connection = MySqlConnection.startConnection();
			PreparedStatement statement = connection.prepareStatement("INSERT INTO uldvelh.object(name) VALUES (?)");
			statement.setString(1, o.getName());
			statement.execute();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void delete(Object o) {
		try {
			Connection connection = MySqlConnection.startConnection();
			PreparedStatement statement = connection.prepareStatement("DELETE FROM uldvelh.object WHERE idObject= ?");
			statement.setInt(1, o.getId());
			statement.execute();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public void update(Object o) {
		try {
			Connection connection = MySqlConnection.startConnection();
			PreparedStatement statement = connection.prepareStatement("UPDATE uldvelh.object SET name=? WHERE idObject= ?");
			statement.setString(1, o.getName());
			statement.setInt(2, o.getId());
			statement.execute();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
