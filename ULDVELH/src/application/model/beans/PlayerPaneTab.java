package application.model.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import application.model.DAO.PlayerDAO;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PlayerPaneTab {
	private TabPane pane;
	private Map<Player , Tab> mapPT= new LinkedHashMap<>();
	private PlayerDAO DAO=new PlayerDAO();
	
	
	public PlayerPaneTab(TabPane pane) {
		this.pane=pane;
	}
	
	public void createAllTab() {
		ArrayList<Player> playerList=DAO.findAllPlayer();
		for(Player p : playerList) {
			mapPT.put(p, new Tab(p.getUsername()));
		}
	}
	
	public void draw() {
		createAllTab();
		Set<Entry<Player, Tab>> setMapPT = mapPT.entrySet();
	    Iterator<Entry<Player, Tab>> iterator = setMapPT.iterator();
	    
	    while(iterator.hasNext()) {
	    	Entry<Player, Tab> e =iterator.next();
	    	drawPlayer(e.getKey(),e.getValue());
	    }
	}
	
	
	public void drawPlayer(Player player,Tab tab) {		
		VBox vbox=new VBox();
		Label healthLabel = new Label("Vie : "+player.getHealth());
		Label currentChapterLabel = new Label("Se trouve au Chapitre : "+player.getIdChapter());
		HBox hboxpriv= new HBox();
		Label privilegeLabel = new Label("Le joueur est Administrateur :");
		CheckBox privileges = new CheckBox();
		
		if(player.getPrivileges()==1) {
			privileges.setSelected(true);
		}
		privileges.setOnAction(e->{
			DAO.updatePrivileges(player.getId(),(privileges.isSelected())? 1 : 0);
		});
		
		hboxpriv.getChildren().addAll(privilegeLabel,privileges);
		vbox.getChildren().addAll(healthLabel,currentChapterLabel,hboxpriv);
		tab.setContent(vbox);
		
		this.pane.getTabs().add(tab);
	}
	
	
}

