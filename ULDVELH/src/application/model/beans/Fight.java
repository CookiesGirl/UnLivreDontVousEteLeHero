package application.model.beans;
import java.util.ArrayList;

//constructeur
public class Fight {
	private int idFight;
	private String textFight;
	private ArrayList<Choice> choices=null;
	private int id;

	
public Fight(int id,String text) {
	this.idFight=id;
	this.textFight = text;
}
public int getId() {
	return idFight;
}
public void setId(int id) {
	this.idFight = id;
	}	
//Nom du combat
public void setText(String text) {
	this.textFight = text;
}
public String getText() {
	return textFight;
}

	// EN COURS //
// startCombat
	
//Fight =
	
//On affiche l'ennemi ET les choix possible
	
//Si choix de fuir = revenir au chap précédent
	
//Sinon combattre = instancie le combat
	
//Le plus haut rank commence
	
}
