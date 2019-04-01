package fr.utbm.ia51.graph.environment;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class ClassLine extends StackPane{
	
	private ImageView classline;
	private Image fieldImage;
		
	
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
}

