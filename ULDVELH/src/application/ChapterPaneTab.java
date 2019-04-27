package application;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ChapterPaneTab {

	private TabPane pane;
	private Tab tab;
	private String name;
	private String story;
	private ArrayList<ChapterPaneTabChoice> choices = new ArrayList<ChapterPaneTabChoice>();
	private int indexChoices;
	
	public ChapterPaneTab(TabPane pane, String name) {
		this.pane= pane;
		VBox vbox = new VBox();
		HBox hbox1= new HBox();
		
		//on créer la zone de texte de l'évenement
		Label eventLabel = new Label("Evènement : ");
		TextArea textArea = new TextArea();
		hbox1.getChildren().addAll(eventLabel,textArea);
		
		//on créer la zone des choix
		VBox vbox2 = new VBox();
		HBox hbox2= new HBox();
		
		//on créer le premier choix (un choix minimum par chapitre)
		choices.add(new ChapterPaneTabChoice("choix",choices.size(),choices.size(),choices,vbox2));
		
		
		//Pour chaque choix enregistré on les rajoutes a l'écran
		for (ChapterPaneTabChoice choice : choices) {
			vbox2.getChildren().add(choice.draw());
		}
		
		//on range tout les élement dans le VBox principal (car l'affichage est en collone
		hbox1.setAlignment(Pos.TOP_CENTER);
		VBox.setMargin(vbox2, new Insets(50, 0, 0, 305));
		vbox.getChildren().addAll(hbox1,vbox2);
		vbox.setAlignment(Pos.TOP_CENTER);
		
		//on rajoute un onglet dans la barre de defilement
		tab = new Tab(name);
		
		//on intègre
		tab.setContent(vbox);
		pane.getTabs().add(tab);
		
		
		
		
		
		//évenement pour sauvegarder la zone de texte
		textArea.textProperty().addListener((observable, oldValue, newValue) -> {
		    this.story=newValue;
		});
		
	}
	
}
