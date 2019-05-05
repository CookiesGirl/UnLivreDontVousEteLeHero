package application.view;

import java.net.URL;
import java.util.ResourceBundle;
import application.model.DAO.ConnectDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ConnectParamController implements Initializable{
	
	@FXML
	private TextField name= new TextField();
	
	@FXML
	private PasswordField pass= new PasswordField();
	
	
	@FXML
	private Button connectButton = new Button();
	
	
	private ConnectDAO DAO = new ConnectDAO();
	
	private Alert alert = new Alert(AlertType.ERROR);
	
	public void tryConnection(ActionEvent event) {
		if(name.getText().isEmpty() || pass.getText().isEmpty()) {
			alert.setHeaderText(null);
			alert.setContentText("Tous les champs doivent être renseignés");
			alert.showAndWait();
		}
		else {
			if(DAO.verifyUsername(name.getText())) {
				if(DAO.verifyPassword(name.getText(), pass.getText())) {
					if(DAO.verifyPrivileges(name.getText(), pass.getText())>0) {
						Stage stage =(Stage)connectButton.getScene().getWindow();
						try {
							Parent root = FXMLLoader.load(getClass().getResource("administrationView.fxml"));
							Stage window = (Stage) stage.getOwner();
							stage.close();
					        window.setScene(new Scene(root));
					        window.setResizable(false);
					        window.centerOnScreen();
					        window.show();
						} catch(Exception e) {
							e.printStackTrace();
						}
					}
					else {
						alert.setHeaderText(null);
						alert.setContentText("Il faut être administrateur pour acceder au parametres");
						alert.showAndWait();
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
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	

}
