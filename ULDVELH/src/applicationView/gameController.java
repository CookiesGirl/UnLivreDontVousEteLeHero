package applicationView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.ChapterPaneTabChoice;
import application.Choice;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class gameController implements Initializable{
	
	@FXML
	private Label chapterText = new Label();

	
	@FXML
	private GridPane choicePane = new GridPane();
	
	
	private ArrayList<Choice> choiceList = new ArrayList<Choice>();
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		chapterText.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin dictum sodales enim, vel semper metus placerat in. Morbi volutpat, metus sed mollis bibendum, dui nisl scelerisque lorem, et molestie ante lorem non nisi. Sed lobortis sit amet ipsum congue fermentum. Sed tempus eros ex, nec iaculis eros auctor non. Mauris eleifend orci ac lectus pellentesque molestie. Cras efficitur lorem mauris, at tempor ex laoreet eu. Phasellus pharetra nisl eu est consectetur, in vestibulum urna scelerisque. Maecenas eget nulla enim. Vestibulum sed lacus risus. Proin at convallis augue, nec scelerisque dolor. Sed sit amet interdum enim. In hac habitasse platea dictumst. Etiam auctor nibh vitae ultricies ullamcorper. Quisque nunc magna, scelerisque eu sapien vel, condimentum dignissim mi. Suspendisse eleifend nibh sit amet vestibulum pellentesque. Curabitur ornare odio et erat scelerisque, id fringilla enim lacinia.");
		
		
		for(int i =0; i<3;i++) {
			choiceList.add(new Choice("Choix "+(i+1), i, i));
		}
		
		choicePane.getChildren().clear();
		int i=0;
		int j=0;
		for(Choice choice : choiceList) {
			Button newButton = new Button();
			newButton.setText(choice.getText());
			choicePane.add(newButton, i, j);
			i++;
			if(i==3) {
				i=0;
				j++;
			}
			
			newButton.setOnAction(e ->{
				System.out.println(choice.getDestination());
			});
			
		}
		
	} 

}
