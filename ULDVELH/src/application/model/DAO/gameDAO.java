package application.model.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import application.model.beans.Chapter;
import application.model.beans.Choice;
import application.model.beans.Character;

@SuppressWarnings("rawtypes")
public class GameDAO extends DAO{

	public Character getCharacterByName(String name) {
		try {
			Connection connection = MySqlConnection.startConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM uldvelh.character WHERE name='"+name+"'");
			
			while(result.next()) {
				Character character =new Character(result.getInt("idCharacter"),result.getString("name"),result.getInt("health"),result.getInt("Chapter_idChapter"));
				MySqlConnection.endConnection();
				return character;
			}			
			MySqlConnection.endConnection();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Chapter getUserChapter(Character c) {
		try {
			Connection connection = MySqlConnection.startConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM uldvelh.chapter WHERE idChapter="+c.getCurrentChapter());
			while(result.next()) {
				int idChapter=result.getInt("idChapter");
				Chapter chapter = new Chapter(idChapter, result.getString("text"));
				MySqlConnection.endConnection();
				return chapter;
			}			
			MySqlConnection.endConnection();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Choice> getChoices(int id){
		ArrayList<Choice> choiceList =new ArrayList<Choice>();
		try {
			Connection connection = MySqlConnection.startConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM uldvelh.choice WHERE Chapter_idChapter="+id);
			while(result.next()) {
				
				choiceList.add(new Choice(result.getInt("idChoice"),result.getString("text"),result.getInt("destination")));
			}			
			MySqlConnection.endConnection();
			return choiceList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Character createCharacter(String username, int userId) {
		try {
			Connection connection = MySqlConnection.startConnection();
			Statement statement = connection.createStatement();
			statement.execute("INSERT INTO uldvelh.bag values("+userId+",'bag-"+username+"');");
			statement.execute("INSERT INTO uldvelh.equipment values("+userId+",null);");
			statement.execute("insert into uldvelh.character values("+userId+",'"+username+"',10,"+userId+","+userId+",1)");
			statement.executeUpdate("UPDATE uldvelh.user SET Character_idCharacter="+userId+" WHERE idUser="+userId);
			MySqlConnection.endConnection();
			return getCharacterByName(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void changeCurrentChapter(Character c,int destination) {
		try {
			Connection connection = MySqlConnection.startConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE uldvelh.character SET Chapter_idChapter="+destination+" WHERE idCharacter="+c.getId());
			MySqlConnection.endConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
