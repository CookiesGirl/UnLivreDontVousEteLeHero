package application.model.beans;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class Choice {
	
	public int index;
	
	private String text;
	
	private int destination;
	
	private int chapter;
	

	public Choice(int index,String text, int destination) {
		this.text=text;
		this.destination=destination;
		this.index=index;
	}
	
	public HBox draw() {
		HBox hbox= new HBox();
		Label choiceName;
		if(index+1<10) {choiceName = new Label("Choix "+0+(index+1)+": ");}
		else {choiceName = new Label("Choix "+(index+1)+": ");};
		TextField choice = new TextField(text);
		Label nextChapter = new Label("qui renvoie vers le chapitre : ");
		TextField destination = new TextField(""+this.destination);
		
		HBox fightbox = new HBox();
		CheckBox fightCheckbox = new CheckBox();
		Label fightLabel = new Label("  et requiert un combat : ");
		fightbox.getChildren().addAll(fightLabel,fightCheckbox);
		
		
		hbox.getChildren().addAll(choiceName,choice,nextChapter,destination,fightbox);

		
		//évenement qui sauvegarde quand on ecrit dans le TextField "destination" 
		destination.textProperty().addListener((observable, oldValue, newValue) -> {
			if(destination.getText().length()!=0) {
				System.out.println(destination.getText().length());
				this.destination=Integer.parseInt(newValue);
			}
		});
		
		
		//évenement qui sauvegarde quand on ecrit dans le TextField "choice" 
		choice.textProperty().addListener((observable, oldValue, newValue) -> {
		    if(choice.getText()!="")this.text=newValue;
		});
		

		
		

		//retourne une Hbox contenant : Nom,Destination et boutons additionnels
		return hbox;
	}
	
	
	
	
	
	
	
	
	
	
	
	public void setChapter(int chapter) {
		this.chapter = chapter;
	}
	public int getChapter() {
		return chapter;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int i) {
		this.index=i;
	}

	public String getText() {
		return this.text;
	}
	
	public int getDestination() {
		return this.destination;
	}

}
