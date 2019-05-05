package application.model.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@SuppressWarnings("rawtypes")
public class ConnectDAO extends DAO{

	public boolean verifyUsername(String username) {
		try {
			Connection connection = MySqlConnection.startConnection();
			Statement statement = connection.createStatement();
			ResultSet result= statement.executeQuery("SELECT username FROM uldvelh.user WHERE username='"+username+"'");
			boolean exist=false;
			if(result.next()) {
				exist=true;
			}
			MySqlConnection.endConnection();
			return exist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return false;
	}
	
	public boolean verifyPassword(String username, String password) {
		try {
			Connection connection = MySqlConnection.startConnection();
			Statement statement = connection.createStatement();
			ResultSet result= statement.executeQuery("SELECT username FROM uldvelh.user WHERE username='"+username+"' AND password=PASSWORD('"+password+"')");
			boolean exist=false;
			if(result.next()) {
				exist=true;
			}
			MySqlConnection.endConnection();
			return exist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return false;
	}
	
	public int verifyPrivileges(String username, String password) {
		try {
			Connection connection = MySqlConnection.startConnection();
			Statement statement = connection.createStatement();
			ResultSet result= statement.executeQuery("SELECT privileges FROM uldvelh.user WHERE username='"+username+"' AND password=PASSWORD('"+password+"')");
			int tmp=0;
			if(result.next()) {
				tmp =result.getInt("privileges");
			}
			MySqlConnection.endConnection();
			return tmp;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getUserHealth(String username) {
		try {
			Connection connection = MySqlConnection.startConnection();
			Statement statement = connection.createStatement();
			ResultSet result= statement.executeQuery("SELECT health FROM uldvelh.character WHERE name='"+username+"'");
			int tmp=0;
			if(result.next()) {
				tmp=result.getInt("health");
			}
			MySqlConnection.endConnection();
			return tmp;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	
	public void createUser(String username,String password) {
		try {
			Connection connection = MySqlConnection.startConnection();
			Statement statement = connection.createStatement();
			statement.execute("INSERT INTO uldvelh.user(username,password,privileges) values('"+username+"',PASSWORD('"+password+"'),0)");
			ResultSet result = statement.executeQuery("SELECT idUser FROM uldvelh.user WHERE username='"+username+"'");
			result.next();
			int idUser=result.getInt("idUser");
			System.out.println(idUser);
			statement.execute("INSERT INTO uldvelh.bag values("+idUser+",'bag-"+username+"')");
			statement.execute("INSERT INTO uldvelh.equipment(idEquipment) values("+idUser+")");
			statement.execute("INSERT INTO uldvelh.character(idCharacter,name,health,Bag_idBag,Equipment_idEquipment,Chapter_idChapter) values("+idUser+",'"+username+"',10,"+idUser+","+idUser+",1)");
			statement.executeUpdate("UPDATE uldvelh.user SET Character_idCharacter ="+idUser+" WHERE idUser="+idUser);
			MySqlConnection.endConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getId(String name) {
		try {
			Connection connection = MySqlConnection.startConnection();
			Statement statement = connection.createStatement();
			ResultSet result= statement.executeQuery("SELECT idUser FROM uldvelh.user WHERE username='"+name+"'");
			int tmp=0;
			if(result.next()) {
				tmp=result.getInt("idUser");
			}
			MySqlConnection.endConnection();
			return tmp;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
	
	
	@Override
	public Object findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object create(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object update(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Object obj) {
		// TODO Auto-generated method stub
		
	}

}
