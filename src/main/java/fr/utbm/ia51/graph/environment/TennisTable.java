package fr.utbm.ia51.graph.environment;

import fr.utbm.ia51.graph.human.ActivityType;
import fr.utbm.ia51.graph.human.GraphHuman;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class TennisTable extends EnvironmentEntity{
	
	private ImageView tennisTable;
	private Image fieldImage;
	private ActivityType TENNISTABLE;
		
	
	public TennisTable(int width) {
		super();
				
		this.fieldImage = new Image("file:src/main/resources/graphism/image/table-tennis-01.png");
		this.tennisTable = new ImageView();
        tennisTable.setImage(this.fieldImage);
        
        tennisTable.setFitWidth(width);
        tennisTable.setPreserveRatio(true);

        this.setMaxSize(this.tennisTable.getFitWidth(), this.tennisTable.getFitHeight());
//        this.setStyle("-fx-border-color : pink");
        
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

