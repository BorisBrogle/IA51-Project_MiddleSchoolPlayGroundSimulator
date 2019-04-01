package fr.utbm.ia51.graph.environment;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Library extends StackPane{
	
	private ImageView book;
	private Image fieldImage;
	private Rectangle building;
		
	
	public Library(int width) {
		super();
				
		this.fieldImage = new Image("file:src/main/resources/graphism/image/book-icon.png");
		this.book = new ImageView();
        book.setImage(this.fieldImage);
        
        book.setFitWidth(width/2);
        book.setPreserveRatio(true);
        
        building= new Rectangle(width, width*0.8, Color.WHITE);
        

        this.setMaxSize(this.building.getWidth(), this.building.getHeight());
        this.setStyle("-fx-border-color : pink");
        
        this.getChildren().addAll(building, book);
	}
}
