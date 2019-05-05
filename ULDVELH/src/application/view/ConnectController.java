package application.view;

import java.net.URL;
import java.util.ResourceBundle;
import application.model.DAO.ConnectDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ConnectController implements Initializable{
	
	@FXML
	private TextField name= new TextField();
	
	@FXML
	private PasswordField pass= new PasswordField();
	
	
	@FXML
	private Button connectButton = new Button();
	
	@FXML
	private Button createButton = new Button();
	
	
	private Alert alert = new Alert(AlertType.ERROR);
	
	private ConnectDAO DAO = new ConnectDAO();

	
	
	public void createUser(ActionEvent event) {
		if(name.getText().isEmpty() || pass.getText().isEmpty()) {
			alert.setHeaderText(null);
			alert.setContentText("Tous les champs doivent être renseignés");
			alert.showAndWait();
		}
		else {
			if(DAO.verifyUsername(name.getText())) {
				alert.setHeaderText(null);
				alert.setContentText("Ce nom d'utilisateur existe déja");
				alert.showAndWait();
			}
			else {
				DAO.createUser(name.getText(), pass.getText());
				tryConnection(event);
			}
		}
	}

	
	public void tryConnection(ActionEvent event) {
		if(name.getText().isEmpty() || pass.getText().isEmpty()) {
			alert.setHeaderText(null);
			alert.setContentText("Tous les champs doivent être renseignés");
			alert.showAndWait();
		}
		else {
			if(DAO.verifyUsername(name.getText())) {
				if(DAO.verifyPassword(name.getText(), pass.getText())) {
					try {
						Stage stage =(Stage)connectButton.getScene().getWindow();
						FXMLLoader loader = new FXMLLoader(getClass().getResource("gameView.fxml"));
						Stage window = (Stage) stage.getOwner();
						stage.close();
				        window.setScene(new Scene(loader.load()));
				        GameController controller =loader.<GameController>getController();
				        controller.initPlayer(name.getText(),DAO.getId(name.getText()));
				        controller.draw();
				        window.setResizable(false);
				        window.centerOnScreen();    
				        window.show();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else {
					alert.setHeaderText(null);
					alert.setContentText("Mot de Passe incorrect");
					alert.showAndWait();
				}
				
			}
			else {
				alert.setHeaderText(null);
				alert.setContentText("Utilisateur Inconnu");
				alert.showAndWait();
			}
			
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	

}
