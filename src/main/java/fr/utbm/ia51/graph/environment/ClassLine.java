package fr.utbm.ia51.graph.environment;

import fr.utbm.ia51.activities.ActivityType;
import fr.utbm.ia51.graph.human.GraphHuman;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class ClassLine extends EnvironmentEntity{
	
	private ImageView classline;
	private Image fieldImage;		
	
	public ClassLine(double width, double height) {
		super();
		
		this.activityType = ActivityType.CLASSLINE;
		this.fieldImage = new Image("file:src/main/resources/graphism/image/classline.png");
		this.classline = new ImageView();
        classline.setImage(this.fieldImage);
        
        classline.setFitWidth(width);
        classline.setFitHeight(height);

        this.setMaxSize(this.classline.getFitWidth(), this.classline.getFitHeight());
        
        this.getChildren().addAll(classline);
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

