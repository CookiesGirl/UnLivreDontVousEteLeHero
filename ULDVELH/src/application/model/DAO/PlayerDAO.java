package application.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import application.model.beans.Player;

public class PlayerDAO extends DAO<Player>{
	
	
	@Override
	public Player findById(int id) {
		Player player = new Player();
		try {
			Connection connection = MySqlConnection.startConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT idUser,username,health,Chapter_idChapter,privileges FROM uldvelh.user INNER JOIN uldvelh.character WHERE idUser=idCharacter AND idUser=?;");
			statement.setInt(1, id);
			ResultSet result =statement.executeQuery();
			while(result.next()) {
				player.setPrivileges(result.getInt("privileges"));
				player.setHealth(result.getInt("health"));
				player.setUsername(result.getString("username"));
				player.setIdChapter(result.getInt("Chapter_idChapter"));
			}
			result.close();
			statement.close();
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
			PreparedStatement statement = connection.prepareStatement("UPDATE uldvelh.user SET privileges=? WHERE idUser=?");
			statement.setInt(1, privileges);
			statement.setInt(2, id);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getLocalizedMessage();
		}
	}
	
	public ArrayList<Player> findAllPlayer(){
		ArrayList<Player> playerList= new ArrayList<Player>();
		try {
			Connection connection = MySqlConnection.startConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT idUser,username,health,Chapter_idChapter,privileges FROM uldvelh.user INNER JOIN uldvelh.character WHERE idUser=idCharacter;");
			ResultSet result =statement.executeQuery();
			while(result.next()) {
				Player player=new Player();
				player.setId(result.getInt("idUser"));
				player.setHealth(result.getInt("health"));
				player.setUsername(result.getString("username"));
				player.setIdChapter(result.getInt("Chapter_idChapter"));
				player.setPrivileges(result.getInt("privileges"));
				playerList.add(player);
			}
			result.close();
			statement.close();
			return playerList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getLocalizedMessage();
		}	
		return null;
	}
	
	
	
	
	
	
	
	@Override
	public void create(Player obj) {
		
	}

	@Override
	public void update(Player obj) {
		
	}

	@Override
	public void delete(Player obj) {
			
	}
	
}
