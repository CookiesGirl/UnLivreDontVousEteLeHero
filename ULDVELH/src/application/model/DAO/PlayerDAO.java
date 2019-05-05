package application.model.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import application.model.beans.Player;

public class PlayerDAO extends DAO<Player>{
	
	
	@Override
	public Player findById(int id) {
		Player player = new Player();
		try {
			Connection connection = MySqlConnection.startConnection();
			Statement statement = connection.createStatement();
			ResultSet result =statement.executeQuery("SELECT idUser,username,health,Chapter_idChapter,privileges FROM uldvelh.user INNER JOIN uldvelh.character WHERE idUser=idCharacter AND idUser="+id+";");
			while(result.next()) {
				player.setPrivileges(result.getInt("privileges"));
				player.setHealth(result.getInt("health"));
				player.setUsername(result.getString("username"));
				player.setIdChapter(result.getInt("Chapter_idChapter"));
			}
			MySqlConnection.endConnection();
			return player;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getLocalizedMessage();
		}
		return null;
	}

	
	
	public void updatePrivileges(int id,int privileges) {
		try {
			Connection connection = MySqlConnection.startConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE uldvelh.user SET privileges="+privileges+" WHERE idUser="+id);
			MySqlConnection.endConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getLocalizedMessage();
		}
	}
	
	public ArrayList<Player> findAllPlayer(){
		ArrayList<Player> playerList= new ArrayList<Player>();
		try {
			Connection connection = MySqlConnection.startConnection();
			Statement statement = connection.createStatement();
			ResultSet result =statement.executeQuery("SELECT idUser,username,health,Chapter_idChapter,privileges FROM uldvelh.user INNER JOIN uldvelh.character WHERE idUser=idCharacter;");
			while(result.next()) {
				Player player=new Player();
				player.setId(result.getInt("idUser"));
				player.setHealth(result.getInt("health"));
				player.setUsername(result.getString("username"));
				player.setIdChapter(result.getInt("Chapter_idChapter"));
				player.setPrivileges(result.getInt("privileges"));
				playerList.add(player);
			}
			MySqlConnection.endConnection();
			return playerList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getLocalizedMessage();
		}	
		return null;
	}
	
	
	
	
	
	
	
	@Override
	public Player create(Player obj) {
		
		return null;
	}

	@Override
	public Player update(Player obj) {
		
		return null;
	}

	@Override
	public void delete(Player obj) {
			
	}
	
}
