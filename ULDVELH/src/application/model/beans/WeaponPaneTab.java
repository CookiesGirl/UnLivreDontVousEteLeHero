package application.model.beans;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import application.model.DAO.WeaponDAO;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WeaponPaneTab {
	private TabPane pane;
	private Map<Weapon,Tab> mapWT = new LinkedHashMap<>();
	WeaponDAO DAO=new WeaponDAO();
	
	
	public WeaponPaneTab(TabPane pane) {
		this.pane = pane;
	}
	
	public void createAllTab() {
		pane.getTabs().clear();
		ArrayList<Weapon> list= DAO.findAll();
		for(Weapon w:list) {
			Tab t = new Tab();
			mapWT.put(w, t);
			drawWeapon(w,t);
		}
	}
	
	public void draw() {
		createAllTab();
	}
	
	public void drawWeapon(Weapon w,Tab t) {
		VBox content = new VBox();
		HBox damageBox= new HBox();
		Label damageLabel = new Label("Dommage de l'arme :");
		TextField damageTextField = new TextField(w.getDamage()+"");
		Button saveButton = new Button("Sauvegarder");
		damageTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			damageTextField.setText(newValue.replaceAll("[^\\d]", "0"));
		});
		saveButton.setOnAction(e->{
			w.setDamage(Integer.parseInt(damageTextField.getText()));
			DAO.update(w);
		});
		damageBox.getChildren().addAll(damageLabel,damageTextField);
		content.getChildren().addAll(damageBox,saveButton);	
		t.setText(w.getName());
		t.setOnClosed(e->{
			deleteWeapon(w);
		});
		t.setContent(content);
		t.setClosable(true);
		pane.getTabs().add(t);
	}
	public void deleteWeapon(Weapon w) {
		DAO.delete(w);
		draw();
	}
	
	public void addWeapon(String name) {
		DAO.create(new Weapon(0,name,0));
		draw();
	}

}
