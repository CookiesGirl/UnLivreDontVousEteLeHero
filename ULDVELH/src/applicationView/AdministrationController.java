package applicationView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.ChapterPaneTab;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AdministrationController implements Initializable{
	
	@FXML
	private Button newButton = new Button();
	
	@FXML
	private TabPane chapterPane = new TabPane();
	
	@FXML
	private TabPane playerPane = new TabPane();
	
	@FXML
	private TabPane monsterPane = new TabPane();
	
	@FXML
	private TextField newChapterTextField = new TextField();
	
	private ArrayList<ChapterPaneTab> tabList = new ArrayList<ChapterPaneTab>();
	
	
	
	public void newChapter() {
		System.out.println(newChapterTextField.getText());
		if(newChapterTextField.getText().isEmpty()!=true) {
			tabList.add(new ChapterPaneTab(chapterPane,"Chapitre "+newChapterTextField.getText())); 
			newChapterTextField.clear();
		}
	}
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		newChapterTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			newChapterTextField.setText(newValue.replaceAll("[^\\d]", ""));
		});
	}

}
