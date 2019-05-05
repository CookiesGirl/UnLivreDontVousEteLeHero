package application.model.beans;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import application.model.DAO.MonsterDAO;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MonsterPaneTab {
	private TabPane pane;
	private Map<Monster,Tab> mapMT = new LinkedHashMap<>();
	MonsterDAO DAO=new MonsterDAO();
	
	public MonsterPaneTab(TabPane pane) {
		this.pane=pane;	
	}
	
	public void createAllTab() {
		mapMT.clear();
		ArrayList<Monster> m= DAO.findAll();
		for(Monster monster :m) {
			Tab tab = new Tab();
			mapMT.put(monster, tab);
			drawMonster(monster,tab);
		}
	}
	
	
	public void draw() {
		createAllTab();
	}
	
	
	public void drawMonster(Monster monster,Tab tab) {

		VBox allContent = new VBox();
		HBox healthHbox= new HBox();
		HBox damageHbox= new HBox();
		HBox rankHbox= new HBox();
		
		Label healthLabel = new Label("Vie : ");
		TextField healthTextField = new TextField(Integer.toString(monster.getHealth()));
		healthHbox.getChildren().addAll(healthLabel,healthTextField);
		healthTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			healthTextField.setText(newValue.replaceAll("[^\\d]", ""));
			if(healthTextField.getLength()==0) {
				healthTextField.setText("0");
			}
			else if(oldValue.equals("0")) {
				healthTextField.setText(newValue.substring(1, newValue.length()));
			}
		});
			
		Label damageLabel = new Label("Dégats : ");
		TextField damageTextField = new TextField(Integer.toString(monster.getDamage()));
		damageHbox.getChildren().addAll(damageLabel,damageTextField);
		damageTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			damageTextField.setText(newValue.replaceAll("[^\\d]", ""));
			if(damageTextField.getLength()==0) {
				damageTextField.setText("0");
			}
			else if(oldValue.equals("0")) {
				damageTextField.setText(newValue.substring(1, newValue.length()));
			}
		});

			
		Label rankLabel = new Label("Rang : ");
		TextField rankTextField = new TextField(Integer.toString(monster.getRank()));
		rankHbox.getChildren().addAll(rankLabel,rankTextField);
		rankTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			rankTextField.setText(newValue.replaceAll("[^\\d]", ""));
			if(rankTextField.getLength()==0) {
				rankTextField.setText("0");
			}
			else if(oldValue.equals("0")) {
				rankTextField.setText(newValue.substring(1, newValue.length()));
			}
		});
			
		Button saveButton= new Button("Sauvegarder");
		saveButton.setOnAction(e->{
			monster.setHealth(Integer.parseInt(healthTextField.getText()));
			monster.setDamage(Integer.parseInt(damageTextField.getText()));
			monster.setRank(Integer.parseInt(rankTextField.getText()));
			DAO.updateMonster(monster);
		});
		
		
		allContent.getChildren().addAll(healthHbox,damageHbox,rankHbox,saveButton);
		tab=new Tab(monster.getName());
		tab.setContent(allContent);	
		tab.setClosable(true);
		tab.setOnClosed(e->{
			deleteMonster(monster);
		});
		
		
		pane.getTabs().add(tab);
	}
	public void addMonster(String name) {
		DAO.addMonster(name, 0, 0, 0);
		pane.getTabs().clear();
		draw();
	}
	
	public void deleteMonster(Monster m) {
		DAO.deleteById(m.getIdMonster());
	}
}
