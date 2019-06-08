package fr.utbm.ia51.graph.environment;

import fr.utbm.ia51.activities.ActivityType;
import fr.utbm.ia51.graph.human.GraphHuman;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Toilet extends EnvironmentEntity{
	
	private ImageView toiletIcon;
	private Image fieldImage;
	private Rectangle building;
		
	
	public Toilet(double width, double height) {
		super();
		
		this.activityType = ActivityType.TOILET;
		this.fieldImage = new Image("file:src/main/resources/graphism/image/toilets.png");
		this.toiletIcon = new ImageView();
        toiletIcon.setImage(this.fieldImage);
        
        toiletIcon.setFitWidth(width);
        toiletIcon.setFitHeight(height);        

        this.setMaxSize(toiletIcon.getFitWidth(), toiletIcon.getFitHeight());
        
        this.getChildren().addAll( toiletIcon);
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

