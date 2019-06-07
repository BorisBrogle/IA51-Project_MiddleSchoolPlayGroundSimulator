package fr.utbm.ia51.graph.environment;


import fr.utbm.ia51.activities.ActivityType;
import fr.utbm.ia51.graph.human.GraphHuman;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class BasketCourt extends EnvironmentEntity{
	
	private ImageView basketCourt;
	private Image fieldImage;		
	
	public BasketCourt(int width) {
		super();
				
		this.activityType = ActivityType.BASKETBALL;
		this.fieldImage = new Image("file:src/main/resources/graphism/image/basket-01.png");
		this.basketCourt = new ImageView();
		basketCourt.setImage(this.fieldImage);
        
		basketCourt.setFitWidth(width);
		basketCourt.setPreserveRatio(true);
       
//        this.setStyle("-fx-border-color : red");
      
        this.setMaxSize(this.basketCourt.getFitWidth(), this.basketCourt.getFitHeight());
        

        
        this.getChildren().addAll(basketCourt);
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
