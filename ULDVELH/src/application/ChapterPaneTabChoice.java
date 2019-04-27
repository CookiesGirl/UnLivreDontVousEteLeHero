package application;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ChapterPaneTabChoice {
	private ArrayList<ChapterPaneTabChoice> parent;
	private VBox box;
	private String name;
	private int Chapitre;
	int index;
	
	
	public ChapterPaneTabChoice(String name, int Chapitre,int index,ArrayList<ChapterPaneTabChoice> parent,VBox box) {
		this.name=name;
		this.Chapitre=Chapitre;
		this.index=index;
		this.parent=parent;
		this.box=box;
	}

	public HBox draw() {
		HBox hbox= new HBox();
		Label choiceName;
		if(index+1<10) {choiceName = new Label("Choix "+0+(index+1)+": ");}
		else {choiceName = new Label("Choix "+(index+1)+": ");};
		TextField choice = new TextField(name);
		Label nextChapter = new Label("qui renvoie vers le chapitre : ");
		TextField destination = new TextField(""+Chapitre);
		
		//creation Bouton "+" et "-"
		Button removeButton =new Button();
		removeButton.setText("-");
		Button addButton =new Button();
		addButton.setText("+");
		
		
		hbox.getChildren().addAll(choiceName,choice,nextChapter,destination);
		
		//Ajout des boutons additionnels si necessaire
		if(parent.size()!=1) {
			
			hbox.getChildren().add(removeButton);
		}
		if(index==parent.size()-1 && parent.size()<9) {
			hbox.getChildren().add(addButton);
		}
		
		
		//évenement qui sauvegarde quand on ecrit dans le TextField "destination" 
		destination.textProperty().addListener((observable, oldValue, newValue) -> {
		    this.Chapitre=Integer.parseInt(newValue);
		});
		
		
		//évenement qui sauvegarde quand on ecrit dans le TextField "choice" 
		choice.textProperty().addListener((observable, oldValue, newValue) -> {
		    this.name=newValue;
		});
		

		//évènement de click dur le bouton "+"
		addButton.setOnAction(e ->{
			parent.add(new ChapterPaneTabChoice("nom",parent.size(),parent.size(),parent,box));
			box.getChildren().clear();
			for (ChapterPaneTabChoice c : parent) {
				box.getChildren().add(c.draw());
			}
		});

		
		//évenement du click sur le bouton "-"
		removeButton.setOnAction(e->{
			parent.remove(index);
			int i=0;
			box.getChildren().clear();
			for (ChapterPaneTabChoice c : parent) {
				c.index=i;
				box.getChildren().add(c.draw());
				i++;
			}
		});
		

		//retourne une Hbox contenant : Nom,Destination et boutons additionnels
		return hbox;
	}
	
}
