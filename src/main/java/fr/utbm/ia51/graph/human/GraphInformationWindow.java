package fr.utbm.ia51.graph.human;

import fr.utbm.ia51.activities.ActivityType;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class GraphInformationWindow extends VBox{
	private Label statusLabel = new Label();
	private Label nameLabel = new Label();
	private Label classLabel = new Label();
	private Label typeLabel = new Label();
	
	
	public GraphInformationWindow(String name,ActivityType activity)
	{
		super();
		this.getStylesheets().add("file:resources/css/informationWindow.css");
		this.getStyleClass().add("information-window");
		
		this.setMouseTransparent(true);
		
		this.nameLabel.setText("Name : "+name);
		this.nameLabel.getStyleClass().add("informationLabel");
		this.classLabel.getStyleClass().add("informationLabel");
		this.typeLabel.setText("Activity : "+ activity);
		this.typeLabel.getStyleClass().add("informationLabel");
		
		//Calculer en multipliant le nombre de label et la taille de la police de caractère
		this.setMaxHeight(4*15);
		this.setMaxWidth(400);
		

		
		this.getChildren().addAll(statusLabel,nameLabel,classLabel,typeLabel);
		
	}
	

}
