package application.view;

import java.net.URL;
import java.util.ResourceBundle;

import application.model.DAO.GameDAO;
import application.model.beans.Chapter;
import application.model.beans.Choice;
import application.model.beans.Character;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameController implements Initializable{
	
	@FXML
	private Label chapterText = new Label();
	
	@FXML
	private GridPane choicePane = new GridPane();

	@FXML
	private Button homeButton = new Button();
	
	private int playerId;
	
	private Chapter chapter;
	
	private Character character;
	
	private GameDAO DAO = new GameDAO();
	
	
	
	
	public void draw() {
		chapterText.setText("L'histoire c'est perdu dans le vortex du temp");
		chapterText.setText(chapter.getText());
		chapterText.setText(chapterText.getText().replace("[pv_hero]", ""+character.getHealth()));		
		
		choicePane.getChildren().clear();
		int i=0;
		int j=0;
		for(Choice choice : chapter.getChoices()) {
			Button choiceButton = new Button();
			choiceButton.setText(choice.getText());
			choicePane.add(choiceButton, i, j);
			i++;
			if(i==3) {
				i=0;
				j++;
			}
			
			choiceButton.setOnAction(e ->{
				DAO.changeCurrentChapter(this.character, choice.getDestination());
				Stage stage =(Stage)choiceButton.getScene().getWindow();
					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("gameView.fxml"));
				        stage.setScene(new Scene(loader.load()));
				        GameController controller =loader.<GameController>getController();
				        controller.initPlayer(this.character.getName(),this.playerId);
				        controller.draw();    
				        stage.show();
					} catch(Exception f) {
						f.printStackTrace();
					}

			});
			
		}
	}
	
	@FXML
	private void goHome(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("menuView.fxml"));
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();    
		        window.setScene(new Scene(root));
		        window.setResizable(false);
		        window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		
	} 
	public void initPlayer(String username,int id) {
		this.playerId=id;
		this.character=DAO.getCharacterByName(username);
		if(character==null) {
			this.character=DAO.createCharacter(username, id);
		}
		
		this.chapter=DAO.getUserChapter(this.character);
		this.chapter.setChoices(DAO.getChoices(this.chapter.getId()));
	}

}