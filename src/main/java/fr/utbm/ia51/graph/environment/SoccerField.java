package fr.utbm.ia51.graph.environment;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class SoccerField extends StackPane{
	
	private ImageView soccerField;
	private Image fieldImage;
		
	
	public SoccerField(int width) {
		super();
				
		this.fieldImage = new Image("file:src/main/resources/graphism/image/footfield-01.png");
		this.soccerField = new ImageView();
		soccerField.setImage(this.fieldImage);
        
		soccerField.setFitWidth(width);
		soccerField.setPreserveRatio(true);
       
        this.setStyle("-fx-border-color : red");
      
        this.setMaxSize(this.soccerField.getFitWidth(), this.soccerField.getFitHeight());
        
        this.getChildren().addAll(soccerField);
	}
}
