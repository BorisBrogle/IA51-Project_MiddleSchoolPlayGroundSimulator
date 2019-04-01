package fr.utbm.ia51.graph.environment;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Toilet extends StackPane{
	
	private ImageView toiletIcon;
	private Image fieldImage;
	private Rectangle building;
		
	
	public Toilet(int width) {
		super();
				
		this.fieldImage = new Image("file:src/main/resources/graphism/image/toilet.png");
		this.toiletIcon = new ImageView();
        toiletIcon.setImage(this.fieldImage);
        
        toiletIcon.setFitWidth(width/3);
        toiletIcon.setPreserveRatio(true);
        
        building= new Rectangle(width, width*0.5, Color.GREY);
        

        this.setMaxSize(this.building.getWidth(), this.building.getHeight());
        this.setStyle("-fx-border-color : pink");
        
        this.getChildren().addAll(building, toiletIcon);
	}
}

