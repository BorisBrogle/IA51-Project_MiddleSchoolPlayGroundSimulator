package fr.utbm.ia51.graph.human;

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
	
	
	public GraphInformationWindow(String status,String name,String className, String type)
	{
		super();
		this.getStylesheets().add("file:src/main/resources/css/informationWindow.css");
		this.getStyleClass().add("information-window");
		
		this.statusLabel.setText("Status : "+status);
		this.statusLabel.getStyleClass().add("informationLabel");
		this.nameLabel.setText("Name : "+name);
		this.nameLabel.getStyleClass().add("informationLabel");
		this.classLabel.setText("Class : "+className);
		this.classLabel.getStyleClass().add("informationLabel");
		this.typeLabel.setText("Type : "+ type);
		this.typeLabel.getStyleClass().add("informationLabel");
		
		//Calculer en multipliant le nombre de label et la taille de la police de caractère
		this.setMaxHeight(4*15);
		this.setMaxWidth(400);
		

		
		this.getChildren().addAll(statusLabel,nameLabel,classLabel,typeLabel);
		
	}
	

}
