package application.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import application.model.beans.ChapterPaneTab;
import application.model.beans.MonsterPaneTab;
import application.model.beans.ObjectPaneTab;
import application.model.beans.PlayerPaneTab;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AdministrationController implements Initializable{
	
	@FXML
	private Button newChapterButton = new Button();
	
	@FXML
	private Button newMonsterButton = new Button();
	
	@FXML
	private Button newObjectButton = new Button();
	
	@FXML
	private Button newWeaponButton = new Button();
	
	@FXML
	private Button homeButton = new Button();
	
	@FXML
	private TabPane chapterPane = new TabPane();
	
	@FXML
	private TabPane playerPane = new TabPane();
	
	@FXML
	private TabPane monsterPane = new TabPane();
	
	@FXML
	private Pane objectPane = new Pane();
	
	@FXML
	private TextField newChapterTextField = new TextField();
	
	@FXML
	private TextField newMonsterTextField = new TextField();
	
	@FXML
	private TextField newObjectTextField = new TextField();
	
	@FXML
	private TextField newWeaponTextField = new TextField();
	
	protected ArrayList<ChapterPaneTab> tabList = new ArrayList<ChapterPaneTab>();
	
	private PlayerPaneTab PPT;
	private MonsterPaneTab MPT;
	private ChapterPaneTab CPT;
	private ObjectPaneTab OPT;
	
	
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
	
	@FXML
	public void newChapter() {
		if(newChapterTextField.getText().isEmpty()!=true) { 
			CPT.addChapter(newChapterTextField.getText());
			newChapterTextField.clear();
		}
	}
	
	@FXML
	private void newMonster() {
		if(newMonsterTextField.getText().isEmpty()!=true) {
			MPT.addMonster(newMonsterTextField.getText());
			newMonsterTextField.clear();
		}
	}
	@FXML
	private void newObject() {
		if(newObjectTextField.getText().isEmpty()!=true) {
			OPT.addObject(newObjectTextField.getText());
			newObjectTextField.clear();
		}
	}
	
	@FXML
	private void newWeapon() {
		if(newWeaponTextField.getText().isEmpty()!=true) {
			OPT.addObject(newWeaponTextField.getText());
			newWeaponTextField.clear();
		}
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		CPT = new ChapterPaneTab(chapterPane);
		CPT.draw();
		
		PPT= new PlayerPaneTab(playerPane);
		PPT.draw();
		
		MPT = new MonsterPaneTab(monsterPane);
		MPT.draw();
		
		OPT = new ObjectPaneTab(objectPane);
		OPT.draw();
		
		
		chapterPane.setTabClosingPolicy(TabClosingPolicy.SELECTED_TAB);
		monsterPane.setTabClosingPolicy(TabClosingPolicy.SELECTED_TAB);
		newChapterTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			newChapterTextField.setText(newValue.replaceAll("[^\\d]", ""));
		});
	}

}
