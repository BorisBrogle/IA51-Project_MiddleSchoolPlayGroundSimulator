package fr.utbm.ia51.graph.environment;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class BasketCourt extends StackPane{
	
	private ImageView basketCourt;
	private Image fieldImage;
		
	
	public BasketCourt(int width) {
		super();
				
		this.fieldImage = new Image("file:src/main/resources/graphism/image/basket-01.png");
		this.basketCourt = new ImageView();
		basketCourt.setImage(this.fieldImage);
        
		basketCourt.setFitWidth(width);
		basketCourt.setPreserveRatio(true);
       
        this.setStyle("-fx-border-color : red");
      
        this.setMaxSize(this.basketCourt.getFitWidth(), this.basketCourt.getFitHeight());
        
        this.getChildren().addAll(basketCourt);
	}
}
