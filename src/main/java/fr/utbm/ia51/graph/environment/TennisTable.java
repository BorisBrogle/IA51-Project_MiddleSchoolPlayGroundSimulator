package fr.utbm.ia51.graph.environment;

import fr.utbm.ia51.activities.ActivityType;
import fr.utbm.ia51.graph.human.GraphHuman;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class TennisTable extends EnvironmentEntity{
	
	private ImageView tennisTable;
	private Image fieldImage;		
	
	public TennisTable(double width, double height) {
		super();
		
		this.activityType = ActivityType.TENNISTABLE;
		this.fieldImage = new Image("file:src/main/resources/graphism/image/tabletennisarea.png");
		this.tennisTable = new ImageView();
        tennisTable.setImage(this.fieldImage);
        
        tennisTable.setFitWidth(width);
        tennisTable.setFitHeight(height);

        this.setMaxSize(this.tennisTable.getFitWidth(), this.tennisTable.getFitHeight());
        
        this.getChildren().addAll(tennisTable);
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

