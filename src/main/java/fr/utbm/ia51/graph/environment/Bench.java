package fr.utbm.ia51.graph.environment;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Bench extends StackPane{
	
	private ImageView bench;
	private Image fieldImage;
		
	
	public Bench(int width) {
		super();
				
		this.fieldImage = new Image("file:src/main/resources/graphism/image/bench.png");
		this.bench = new ImageView();
        bench.setImage(this.fieldImage);
        
        bench.setFitWidth(width);
        bench.setPreserveRatio(true);

        this.setMaxSize(this.bench.getFitWidth(), this.bench.getFitHeight());
        this.setStyle("-fx-border-color : pink");
        
        this.getChildren().addAll(bench);
	}
}

