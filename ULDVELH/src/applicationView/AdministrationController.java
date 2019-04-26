package applicationView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.PaneTab;
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
	private TabPane pane = new TabPane();
	
	@FXML
	private TextField newChapterTextField = new TextField();
	
	private ArrayList<PaneTab> tabList = new ArrayList<PaneTab>();
	
	
	
	public void newChapter() {
		tabList.add(new PaneTab(pane,"Chapitre "+newChapterTextField.getText())); 
		newChapterTextField.clear();
	}
	


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("started");
		tabList.add(new PaneTab(pane,"prototype"));



	}

}
