package application.model.beans;


import java.util.ArrayList;
import application.model.DAO.ObjectDAO;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ObjectPaneTab {
	private Pane pane;
	private VBox box = new VBox();
	private ArrayList<Object> objectList = new ArrayList<Object>();
	private ObjectDAO DAO=new ObjectDAO();
	
	
	public ObjectPaneTab(Pane pane) {
		this.pane = pane;
		objectList= DAO.findAll();
	}
	
	
	public void draw() {
		box.getChildren().clear();
		pane.getChildren().clear();
		for(Object object :objectList) {
			drawObject(object);
		}
		pane.getChildren().add(box);
	}
	
	public void drawObject(Object o) {
		HBox content = new HBox();
		Label name = new Label(o.getName());
		Button delete = new Button();
		delete.setText("-");
		
		delete.setOnAction(e->{
			DAO.delete(o);
			objectList= DAO.findAll();
			draw();
			
		});
		
		content.getChildren().addAll(delete,name);
		box.getChildren().add(content);
	}
	
	public void addObject(String name) {
		Object n = new Object(0,name);
		DAO.create(n);
		objectList= DAO.findAll();
		draw();
	}
}
