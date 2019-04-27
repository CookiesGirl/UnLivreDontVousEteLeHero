package applicationView;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

public class menuController {
	
	private Button playButton 	= new Button();
	private Button adminButton	= new Button();
	private Button quitButton	= new Button();
	
	
	public void play(ActionEvent event) {
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("gameView.fxml"));
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();    
		        window.setScene(new Scene(root));
		        window.setResizable(false);
		        window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void admin(ActionEvent event) {
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("administrationView.fxml"));
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();    
		        window.setScene(new Scene(root));
		        window.setResizable(false);
		        window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void quit() {
		Platform.exit();
		
	}

}
