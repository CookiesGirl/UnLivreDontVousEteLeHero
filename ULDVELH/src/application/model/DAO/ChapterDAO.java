package application.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import application.model.beans.Chapter;
import application.model.beans.Choice;


public class ChapterDAO extends DAO<Chapter>{
	
	public ArrayList<Chapter> findAll(){
		ArrayList<Chapter> chapterList =new ArrayList<Chapter>();
		try {
			Connection connection = MySqlConnection.startConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM uldvelh.chapter ORDER BY idChapter");
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				int id=result.getInt("idChapter");
				chapterList.add(new Chapter(id,result.getString("text")));
			}			
			result.close();
			statement.close();
			return chapterList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	@Override
	public Chapter findById(int id){
		Chapter chapter =null;
		try {
			Connection connection = MySqlConnection.startConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM uldvelh.chapter ORDER BY idChapter");
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				int id1=result.getInt("idChapter");
				chapter =new Chapter(id1,result.getString("text"));
			}			
			result.close();
			statement.close();
			return chapter;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArrayList<Choice> getChoices(int id){
		ArrayList<Choice> choiceList =new ArrayList<Choice>();
		try {
			Connection connection = MySqlConnection.startConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM uldvelh.choice WHERE Chapter_idChapter=?");
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				
				choiceList.add(new Choice(result.getInt("idChoice"),result.getString("text"),result.getInt("destination")));
			}			
			result.close();
			statement.close();
			return choiceList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateChoices(int id,ArrayList<Choice> choices) {
		try {
			Connection connection = MySqlConnection.startConnection();
			PreparedStatement statement = connection.prepareStatement("DELETE FROM uldvelh.choice WHERE Chapter_idChapter=?");
			statement.setInt(1, id);
			statement.execute();
			statement.close();
			for(Choice c :choices) {
				statement = connection.prepareStatement("INSERT INTO uldvelh.choice(idChoice,text,destination,Chapter_idChapter,Fight_id) VALUES ( ? , ? , ? , ? , NULL )");
				statement.setInt(1, c.getIndex());
				statement.setString(2, c.getText());
				statement.setInt(3, c.getDestination());
				statement.setInt(4, c.getChapter());
				statement.execute();
			}
					
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean verifyUsage(int id) {
		try {
			Connection connection = MySqlConnection.startConnection();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM uldvelh.character WHERE Chapter_idChapter=?");
			statement.setInt(1, id);
			ResultSet result =statement.executeQuery();
			if(result.next()) {
				result.close();
				statement.close();
				return true;
			}		
			result.close();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public void delete(Chapter chapter) {
		try {
			Connection connection = MySqlConnection.startConnection();
			PreparedStatement statement = connection.prepareStatement("DELETE FROM uldvelh.chapter WHERE idChapter=?");
			statement.setInt(1, chapter.getId());
			statement.execute();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void update(Chapter chapter) {
		try {
			String formatedText = chapter.getText().replace("'","\\'").replace('"','\"');
			Connection connection = MySqlConnection.startConnection();
			PreparedStatement statement = connection.prepareStatement("UPDATE uldvelh.chapter SET text= ? WHERE idChapter= ?");
			statement.setString(1, formatedText);
			statement.setInt(2, chapter.getId());
			statement.execute();
			statement.close();
			updateChoices(chapter.getId(),chapter.getChoices());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void create(Chapter chapter) {
		try {
			Connection connection = MySqlConnection.startConnection();
			PreparedStatement statement = connection.prepareStatement("INSERT INTO uldvelh.chapter(idChapter,text) VALUES ( ? ,' ')");
			statement.setString(1,chapter.getId()+"");
			statement.execute();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
