package fr.utbm.ia51.graph.environment;

import fr.utbm.ia51.activities.ActivityType;
import fr.utbm.ia51.graph.human.GraphHuman;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Library extends EnvironmentEntity{
	
	private ImageView library;
	private Image fieldImage;
	private Rectangle building;		
	
	public Library(double width, double height) {
		super();
		
		this.activityType = ActivityType.LIBRARY;
		this.fieldImage = new Image("file:src/main/resources/graphism/image/library.png");
		this.library = new ImageView();
		library.setImage(this.fieldImage);
        
		library.setFitWidth(width);
		library.setFitHeight(height);
        

        this.setMaxSize(this.library.getFitWidth(), this.library.getFitHeight());
        
        this.getChildren().addAll(library);
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

