package fr.utbm.ia51.graph.environment;

import fr.utbm.ia51.activities.ActivityType;
import fr.utbm.ia51.graph.human.GraphHuman;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Bench extends EnvironmentEntity{
	
	private ImageView bench;
	private Image fieldImage;
	private ActivityType BENCH;
		
	
	public Bench(int width) {
		super();
				
		this.fieldImage = new Image("file:src/main/resources/graphism/image/bench.png");
		this.bench = new ImageView();
        bench.setImage(this.fieldImage);
        
        bench.setFitWidth(width);
        bench.setPreserveRatio(true);

        this.setMaxSize(this.bench.getFitWidth(), this.bench.getFitHeight());
//        this.setStyle("-fx-border-color : pink");
        
        this.getChildren().addAll(bench);
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

