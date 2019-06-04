package fr.utbm.ia51.activities;

import javafx.beans.property.DoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ActivityToolTip extends StackPane{
	
	private ImageView tooltip;
	private Image fieldImage;
	private Circle position;
	
	public ActivityToolTip(ActivityType type) {
		super();
		
		//ActivityType type = ActivityType.BASKETBALL;		
		this.tooltip = new ImageView();
		
		this.changeDesire(type);
		
		tooltip.setImage(this.fieldImage);
        
		tooltip.setFitWidth(30);
		tooltip.setPreserveRatio(true);
       
//        this.setStyle("-fx-border-color : red");
        this.position = new Circle(1);
        this.position.setFill(Color.TRANSPARENT);
        //this.position.setStyle("-fx-border-color : transparent");
        
        this.getChildren().addAll(tooltip,position);
	}
	
	public DoubleProperty getXPosition() {
		return this.position.centerXProperty();
	}
	
	public DoubleProperty getYPosition() {
		return this.position.centerYProperty();
	}
	
	public Circle getPosition(){
		return this.position;
	}
	
	
	
	public void changeDesire(ActivityType type) {
		switch (type) {
		case TENNISTABLE:
			this.fieldImage = new Image("file:src/main/resources/graphism/image/tooltip-tennis-table.png");
			break;
			
		case SOCCER:
			this.fieldImage = new Image("file:src/main/resources/graphism/image/tooltip-football.png");
			break;
		
		case BASKETBALL:
			this.fieldImage = new Image("file:src/main/resources/graphism/image/tooltip-basketball.png");
			break;
		
		case BENCH:
			this.fieldImage = new Image("file:src/main/resources/graphism/image/tooltip-bench.png");
			break;
			
		case LIBRARY:
			this.fieldImage = new Image("file:src/main/resources/graphism/image/tooltip-library.png");
			break;
			
		case CLASSLINE:
			this.fieldImage = new Image("file:src/main/resources/graphism/image/tooltip-classline.png");
			break;
		
		case FOREST:
			this.fieldImage = new Image("file:src/main/resources/graphism/image/tooltip-forest.png");
			break;
		
		case TOILET:
			this.fieldImage = new Image("file:src/main/resources/graphism/image/tooltip-toilet.png");
			break;
			
		case FIGHT:
			this.fieldImage = new Image("file:src/main/resources/graphism/image/tooltip-fight.png");
			break;
 
		default:
			this.fieldImage = new Image("file:src/main/resources/graphism/image/tooltip-01.png");
			break;
		}
		
		tooltip.setImage(this.fieldImage);
	}
	
	/*public enum ActivityType {
		TENNISTABLE,
		FOOTBALL,
		BASKETBALL,
		BENCH,
		LIBRARY,
		CLASSLINE,
		FOREST,
		TOILET,
		FIGHT;
	}*/

}

