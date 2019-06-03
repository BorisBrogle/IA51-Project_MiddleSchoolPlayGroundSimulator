package fr.utbm.ia51.graph.environment;

import fr.utbm.ia51.graph.human.ActivityType;
import fr.utbm.ia51.graph.human.GraphHuman;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class ClassLine extends EnvironmentEntity{
	
	private ImageView classline;
	private Image fieldImage;
	private ActivityType CLASSLINE;
		
	
	public ClassLine(int width) {
		super();
				
		this.fieldImage = new Image("file:src/main/resources/graphism/image/class-line.png");
		this.classline = new ImageView();
        classline.setImage(this.fieldImage);
        
        classline.setFitWidth(width);
        classline.setPreserveRatio(true);

        this.setMaxSize(this.classline.getFitWidth(), this.classline.getFitHeight());
        this.setStyle("-fx-border-color : pink");
        
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

