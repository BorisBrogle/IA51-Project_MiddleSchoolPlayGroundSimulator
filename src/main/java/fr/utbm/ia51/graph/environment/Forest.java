package fr.utbm.ia51.graph.environment;

import fr.utbm.ia51.activities.ActivityType;
import fr.utbm.ia51.graph.human.GraphHuman;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Forest extends EnvironmentEntity{
	
	private ImageView forest;
	private Image fieldImage;
	private ActivityType FOREST;
		
	
	public Forest(int width) {
		super();
				
		this.fieldImage = new Image("file:src/main/resources/graphism/image/forest-01.png");
		this.forest = new ImageView();
        forest.setImage(this.fieldImage);
        
        forest.setFitWidth(width);
        forest.setPreserveRatio(true);

        this.setMaxSize(this.forest.getFitWidth(), this.forest.getFitHeight());
//        this.setStyle("-fx-border-color : pink");
        
        this.getChildren().addAll(forest);
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

