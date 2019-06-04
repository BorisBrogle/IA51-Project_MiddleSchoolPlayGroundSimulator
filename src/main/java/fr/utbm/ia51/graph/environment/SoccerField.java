package fr.utbm.ia51.graph.environment;

import fr.utbm.ia51.activities.ActivityType;
import fr.utbm.ia51.graph.human.GraphHuman;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class SoccerField extends EnvironmentEntity{
	
	private ImageView soccerField;
	private Image fieldImage;
	private ActivityType SOCCER;
		
	
	public SoccerField(int width) {
		super();
				
		this.fieldImage = new Image("file:src/main/resources/graphism/image/footfield-01.png");
		this.soccerField = new ImageView();
		soccerField.setImage(this.fieldImage);
        
		soccerField.setFitWidth(width);
		soccerField.setPreserveRatio(true);
       
//        this.setStyle("-fx-border-color : red");
      
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
