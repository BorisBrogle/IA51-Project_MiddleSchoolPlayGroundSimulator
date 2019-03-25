package fr.utbm.ia51.graph.human;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GraphHuman extends StackPane {
	

	
	
	
	private Rectangle arms;
	private Circle head = new Circle();
	private Circle lefteye, righteye;
	private Label nameLabel;
	
	
	//Add position 
	
	
	
	public GraphHuman (int x,int y, String headStyle, String armStyle, int sizeRatioHead) {

		super();
		this.setStyle("-fx-border-color : red");
		this.setRotate(45);
		this.setTranslateX(x);
		this.setTranslateY(y);
		this.head.setCenterX(x);
		this.head.setCenterY(y);
		this.head.setRadius(6*sizeRatioHead);
		
		

		
		
		this.lefteye = new Circle(sizeRatioHead);
		this.righteye = new Circle(sizeRatioHead);
		
		this.lefteye.translateXProperty().bind(head.translateXProperty().subtract(3*sizeRatioHead));
		this.righteye.translateXProperty().bind(head.translateXProperty().add(3*sizeRatioHead));
		this.lefteye.translateYProperty().bind(head.translateYProperty().add(3*sizeRatioHead));
		this.righteye.translateYProperty().bind(head.translateXProperty().add(3*sizeRatioHead));
		
		
		
		
		this.arms = new Rectangle(x-4,y-4,18*sizeRatioHead,6*sizeRatioHead);
		this.arms.setFill(Color.GREEN);
		this.getChildren().addAll(arms,head,lefteye,righteye);
		
				
	}
	

}
