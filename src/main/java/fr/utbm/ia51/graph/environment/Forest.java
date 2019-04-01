package fr.utbm.ia51.graph.environment;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Forest extends StackPane{
	
	private ImageView forest;
	private Image fieldImage;
		
	
	public Forest(int width) {
		super();
				
		this.fieldImage = new Image("file:src/main/resources/graphism/image/forest-01.png");
		this.forest = new ImageView();
        forest.setImage(this.fieldImage);
        
        forest.setFitWidth(width);
        forest.setPreserveRatio(true);

        this.setMaxSize(this.forest.getFitWidth(), this.forest.getFitHeight());
        this.setStyle("-fx-border-color : pink");
        
        this.getChildren().addAll(forest);
	}
}

