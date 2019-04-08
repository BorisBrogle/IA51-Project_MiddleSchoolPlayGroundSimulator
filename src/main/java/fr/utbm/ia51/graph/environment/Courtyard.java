package fr.utbm.ia51.graph.environment;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Courtyard extends StackPane{
	
	private Rectangle building;
		
	
	public Courtyard(double width, double height) {
		super();
        
        building= new Rectangle(width, height, Color.LIGHTGREY);
        

        this.setMaxSize(this.building.getWidth(), this.building.getHeight());
        this.setStyle("-fx-border-color : pink");
        
        this.getChildren().addAll(building);
	}
}

