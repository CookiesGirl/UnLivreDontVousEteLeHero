package application;

import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Choice {
	private ArrayList<Choice> parent;
	private VBox box;
	private String name;
	private int Chapitre;
	int index;
	
	
	public Choice(String name, int Chapitre,int index,ArrayList<Choice> parent,VBox box) {
		this.name=name;
		this.Chapitre=Chapitre;
		this.index=index;
		this.parent=parent;
		this.box=box;
	}

	public HBox administrationView() {
		HBox hbox= new HBox();
		Label choiceName;
		if(index+1<10) {choiceName = new Label("Choix "+0+(index+1)+": ");}
		else {choiceName = new Label("Choix "+(index+1)+": ");};
		TextField choice = new TextField(name);
		Label nextChapter = new Label("Chapitre qui renvoie : ");
		TextField destination = new TextField(""+Chapitre);
		
		//creation Bouton "+" et "-"
		Button removeButton =new Button();
		removeButton.setText("-");
		Button addButton =new Button();
		addButton.setText("+");
		
		//Ajout des boutons additionnels si necessaire
		if(index!=0) {
			
			hbox.getChildren().addAll(choiceName,choice,nextChapter,destination,removeButton);
		}
		else {
			hbox.getChildren().addAll(choiceName,choice,nextChapter,destination);
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
			parent.add(new Choice("nom",parent.size(),parent.size(),parent,box));
			box.getChildren().clear();
			for (Choice c : parent) {
				box.getChildren().add(c.administrationView());
			}
		});

		
		//évenement du click sur le bouton "-"
		removeButton.setOnAction(e->{
			System.out.println("il faut supprimer la ligne "+index);
			parent.remove(index);
			int i=0;
			box.getChildren().clear();
			for (Choice c : parent) {
				c.index=i;
				box.getChildren().add(c.administrationView());
				i++;
			}
		});
		
		//retourne une Hbox contenant : Nom,Destination et boutons additionnels
		return hbox;
	}
	
}
