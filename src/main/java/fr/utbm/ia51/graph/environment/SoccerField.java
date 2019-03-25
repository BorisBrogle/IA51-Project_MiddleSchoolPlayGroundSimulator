package fr.utbm.ia51.graph.environment;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class SoccerField extends StackPane{
	
	private ImageView grass;
	private Image fieldImage;
		
	
	public SoccerField(int x, int y) {
		super();
				
		this.fieldImage = new Image("file:src/main/resources/graphism/image/footfield-01.png");
		this.grass = new ImageView();
        grass.setImage(this.fieldImage);
        
        grass.setFitWidth(x);
        grass.setPreserveRatio(true);
        this.setStyle("-fx-border-color : red");
        this.setPrefSize(300, 100);
        this.getChildren().addAll(grass);
	}
}
