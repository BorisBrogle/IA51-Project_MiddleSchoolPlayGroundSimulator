package fr.utbm.ia51.graph.environment;


import fr.utbm.ia51.activities.ActivityType;
import fr.utbm.ia51.graph.human.GraphHuman;
import javafx.scene.shape.Rectangle;

public class BasketCourt extends EnvironmentEntity{	
	public BasketCourt(String imagePath) {
		super(imagePath);
		this.activityType = ActivityType.BASKETBALL;
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
