package application.model.beans;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import application.model.DAO.ChapterDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ChapterPaneTab {
	private TabPane pane;
	private Map<Chapter,Tab> mapCT = new LinkedHashMap<>(); 
	private Alert alert = new Alert(AlertType.ERROR);
	private ChapterDAO DAO = new ChapterDAO();
	
	
	public ChapterPaneTab(TabPane pane) {
		this.pane=pane;
	}
	
	public void createAllTab() {
		mapCT.clear();
		ArrayList<Chapter> chapters =DAO.findAll();
		for(Chapter c: chapters) {
			c.setChoices(DAO.getChoices(c.getId()));
			Tab tab =new Tab();
			mapCT.put(c, tab);
			drawChapter(c,tab);
		}
	}
	public void refresh(Chapter chapter) {
		int i=0;
		for(Choice c:chapter.getChoices()) {
			c.setIndex(i);
			i++;
		}
		drawChapter(chapter,mapCT.get(chapter));
	}
	
	
	public void draw() {
		createAllTab();
	}
	
	public void drawChapter(Chapter chapter,Tab tab) {
		VBox vbox = new VBox();
		HBox hbox1= new HBox();
		TextArea textArea = new TextArea();
		//on créer la zone de texte de l'évenement
		Label eventLabel = new Label("Evènement : ");
		textArea.setText(chapter.getText());
		hbox1.getChildren().addAll(eventLabel,textArea);
		
		//on créer la zone des choix
		VBox vbox2 = new VBox();		
		
		if(chapter.getChoices().size()==0) {
			try {
				chapter.getChoices().add(new Choice(chapter.getChoices().size(),"Evenement",1));
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//Pour chaque choix enregistré on les rajoutes a l'écran
		for (Choice choice : chapter.getChoices()) {
			HBox content =choice.draw();
			
			if(chapter.getChoices().size()>1) {
				Button removeButton =new Button();
				removeButton.setText("-");
				content.getChildren().add(removeButton);
				//évenement du click sur le bouton "-"
				removeButton.setOnAction(e->{
					chapter.getChoices().remove(choice.index);
					refresh(chapter);
				});
			}
			if(choice.getIndex()==chapter.getChoices().size()-1 && chapter.getChoices().size()<9) {
				Button addButton =new Button();
				addButton.setText("+");
				content.getChildren().add(addButton);
				//évènement de click dur le bouton "+"
				addButton.setOnAction(e ->{
					Choice c =new Choice(chapter.getChoices().size(),"nom",1);
					chapter.getChoices().add(c);
					chapter.refreshChoices();
					refresh(chapter);
				});
			}
			vbox2.getChildren().add(content);
		}
		
		//on range tout les élement dans le VBox principal (car l'affichage est en collone
		hbox1.setAlignment(Pos.TOP_CENTER);
		VBox.setMargin(vbox2, new Insets(50, 0, 0, 305));
		Button saveButton= new Button();
		saveButton.setText("Sauvegarder");
		vbox.getChildren().addAll(hbox1,vbox2,saveButton);
		vbox.setAlignment(Pos.TOP_CENTER);
		
		//on rajoute un onglet dans la barre de defilement
		//tab = new Tab("Chapitre "+chapter.getId());
		tab.setText("Chapitre "+chapter.getId());
		tab.setOnClosed(e->{
			if(DAO.verifyUsage(chapter.getId())) {
				pane.getTabs().clear();
				this.draw();
			    this.alert.setTitle("Attention");
				alert.setHeaderText(null);
				alert.setContentText("Impossible d'effacer ce Chapitre car il est utilisé par un ou plusieurs joueurs !");
				alert.showAndWait();
				
			}
			else {
				DAO.deleteChapter(chapter.getId());
				mapCT.remove(chapter);
			}
		});
		//on intègre
		tab.setContent(vbox);
		pane.getTabs().add(tab);
		
		
		saveButton.setOnAction(e->{
			if(chapter.getText().length()==0) {
				chapter.setText(" ");
			}
			chapter.setText(textArea.getText());
			DAO.updateChapter(chapter);
		});
		
		
		//évenement pour sauvegarder la zone de texte
		textArea.textProperty().addListener((observable, oldValue, newValue) -> {
			if(textArea.getLength()>2000) {
				textArea.setText(newValue.substring(0, 2000));
			}		    
		});
		
	}
	
	public void addChapter(String name) {
		DAO.createChapter(name);
		pane.getTabs().clear();
		draw();
	}
}
