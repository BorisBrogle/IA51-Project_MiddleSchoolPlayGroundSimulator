package fr.utbm.ia51.graph.environment;

import fr.utbm.ia51.graph.human.GraphHuman;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Courtyard extends EnvironmentEntity{
	
	private Rectangle building;
		
	
	public Courtyard(double width, double height) {
		super();
        
        building= new Rectangle(width, height, Color.LIGHTGREY);
        

        this.setMaxSize(this.building.getWidth(), this.building.getHeight());
//        this.setStyle("-fx-border-color : pink");
        
        this.getChildren().addAll(building);
	}
	
	public Rectangle getArea() {
		return new Rectangle(this.getTranslateX(),this.getTranslateY(),this.getWidth(),this.getHeight());
		
	}
	
	public boolean intersect(GraphHuman g) {
		if(this.getArea().intersects(g.getLayoutBounds()))
			return true;
		return false;
	}
}

