package fr.utbm.ia51.graph.environment;

import fr.utbm.ia51.activities.ActivityType;
import fr.utbm.ia51.graph.human.GraphHuman;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class SoccerField extends EnvironmentEntity {
	
	private ImageView soccerField;
	private Image fieldImage;		
	
	public SoccerField(double width, double height) {
		super();
		
		this.activityType = ActivityType.SOCCER;
		this.fieldImage = new Image("file:src/main/resources/graphism/image/soccerfield.png");
		this.soccerField = new ImageView();
		soccerField.setImage(this.fieldImage);
        
		soccerField.setFitWidth(width);
		soccerField.setFitHeight(height);
       
		//this.setStyle("-fx-border-color : red");
      
        this.setMaxSize(this.soccerField.getFitWidth(), this.soccerField.getFitHeight());
        
        this.getChildren().addAll(soccerField);
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
