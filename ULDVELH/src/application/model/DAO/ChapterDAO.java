package application.model.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import application.model.beans.Chapter;
import application.model.beans.Choice;


public class ChapterDAO extends DAO<Chapter>{
	
	public ArrayList<Chapter> findAll(){
		ArrayList<Chapter> chapterList =new ArrayList<Chapter>();
		try {
			Connection connection = MySqlConnection.startConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM uldvelh.chapter ORDER BY idChapter");
			while(result.next()) {
				int id=result.getInt("idChapter");
				chapterList.add(new Chapter(id,result.getString("text")));
			}			
			MySqlConnection.endConnection();
			return chapterList;
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

	public void updateChoices(int id,ArrayList<Choice> choices) {
		try {
			Connection connection = MySqlConnection.startConnection();
			Statement statement = connection.createStatement();
			statement.execute("DELETE FROM uldvelh.choice WHERE Chapter_idChapter="+id);
			
			for(Choice c :choices) {
				statement.execute("INSERT INTO uldvelh.choice(idChoice,text,destination,Chapter_idChapter,Fight_id) VALUES ("+c.getIndex()+",'"+c.getText()+"',"+c.getDestination()+","+c.getChapter()+",NULL)");
			}
					
			MySqlConnection.endConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean verifyUsage(int id) {
		try {
			Connection connection = MySqlConnection.startConnection();
			Statement statement = connection.createStatement();
			ResultSet result =statement.executeQuery("SELECT * FROM uldvelh.character WHERE Chapter_idChapter="+id);
			while(result.next()) {
				MySqlConnection.endConnection();
				return true;
			}		
			MySqlConnection.endConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void deleteChapter(int id) {
		try {
			Connection connection = MySqlConnection.startConnection();
			Statement statement = connection.createStatement();
			statement.execute("DELETE FROM uldvelh.chapter WHERE idChapter="+id);
			MySqlConnection.endConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateChapter(Chapter chapter) {
		try {
			String formatedText = chapter.getText().replace("'","\\'").replace('"','\"');
			Connection connection = MySqlConnection.startConnection();
			Statement statement = connection.createStatement();
			statement.execute("UPDATE uldvelh.chapter SET text='"+formatedText+"' WHERE idChapter="+chapter.getId()+"");
			MySqlConnection.endConnection();
			updateChoices(chapter.getId(),chapter.getChoices());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createChapter(String name) {
		try {
			Connection connection = MySqlConnection.startConnection();
			Statement statement = connection.createStatement();
			statement.execute("INSERT INTO uldvelh.chapter(idChapter,text) VALUES ('"+name+"',' ')");
			MySqlConnection.endConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public Chapter findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Chapter create(Chapter obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Chapter update(Chapter obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Chapter obj) {
		// TODO Auto-generated method stub
		
	}

}
