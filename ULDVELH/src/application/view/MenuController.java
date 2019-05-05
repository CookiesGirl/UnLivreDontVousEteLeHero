package application.view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {
	@FXML
	private Button playButton 	= new Button();
	@FXML
	private Button adminButton	= new Button();
	@FXML
	private Button quitButton	= new Button();
	
	
	
	private boolean connect(ActionEvent event) {
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("connectView.fxml"));
			Stage window = new Stage();    
		        window.setScene(new Scene(root));
		        window.setResizable(false);
		        window.centerOnScreen();
		        window.initOwner((Stage)((Node)event.getSource()).getScene().getWindow());
		        window.showAndWait();
		        return true;
		} catch(Exception e) {
			e.getLocalizedMessage();
		}
		return false;
	}
	
	public void play(ActionEvent event) {
		connect(event);
	}
	
	public boolean admin(ActionEvent event) {
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("connectParamView.fxml"));
			Stage window = new Stage();    
	        window.setScene(new Scene(root));
	        window.setResizable(false);
	        window.centerOnScreen();
	        window.initOwner((Stage)((Node)event.getSource()).getScene().getWindow());
	        window.showAndWait();
	        return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	public void quit() {
		Platform.exit();
		
	}

}
