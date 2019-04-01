package fr.utbm.ia51.graph.human;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class GraphHuman extends StackPane {
	
	private Rectangle arms;
	private Circle head = new Circle();
	private Circle lefteye, righteye;
	private Label nameLabel;
	
	
	//Add position 
	
	
	
	public GraphHuman (int x,int y, String headStyle, String armStyle, int sizeRatioHead,String name) {

		super();
		this.setTranslateX(x);
		this.setTranslateY(y);
		this.head.setCenterX(x);
		this.head.setCenterY(y);
		this.head.setRadius(6*sizeRatioHead);
		this.head.setFill(Color.NAVAJOWHITE);
		
		
		this.lefteye = new Circle(sizeRatioHead);
		this.righteye = new Circle(sizeRatioHead);
		
		this.lefteye.translateXProperty().bind(head.translateXProperty().subtract(3*sizeRatioHead));
		this.righteye.translateXProperty().bind(head.translateXProperty().add(3*sizeRatioHead));
		this.lefteye.translateYProperty().bind(head.translateYProperty().add(3*sizeRatioHead));
		this.righteye.translateYProperty().bind(head.translateXProperty().add(3*sizeRatioHead));
		
			
		this.arms = new Rectangle(x-4,y-4,18*sizeRatioHead,6*sizeRatioHead);
		this.arms.setFill(Color.GREEN);
		
		this.nameLabel=new Label();
		this.nameLabel.translateXProperty().bind(head.translateXProperty().subtract(head.getRadius()/2));
		this.nameLabel.translateYProperty().bind(head.translateYProperty().subtract((head.getRadius()+5)*sizeRatioHead));
		this.nameLabel.setMaxWidth(arms.getWidth());
		this.nameLabel.setMaxHeight(arms.getHeight());
		
		if(name!=null) 
			this.nameLabel.setText("Gudule");
			
		this.nameLabel.setText(name);
		this.nameLabel.setFont(new Font(sizeRatioHead*4));
		
		this.head.setOnMouseEntered(e->this.nameLabel.setVisible(true));
		this.head.setOnMouseExited(e->this.nameLabel.setVisible(false));
		
		this.prefWidthProperty().bind(this.arms.widthProperty().add(10));
		this.setStyle("-fx-border-color : red");
		
		this.getChildren().addAll(arms,head,lefteye,righteye,nameLabel);
		
	}
	
	
	public void rotateGraph(int rotate) {
		System.out.println("GetWidht "+this.widthProperty().doubleValue()+" GetHeight"+this.getHeight());
		Rotate rotation = new Rotate(rotate,this.head.getTranslateX(),this.head.getTranslateY());
		this.getTransforms().add(rotation);
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
            new KeyFrame(Duration.ZERO, new KeyValue(rotation.angleProperty(), rotate)),
            new KeyFrame(new Duration(1000), new KeyValue(rotation.angleProperty(), -rotate))
        );
        timeline.setAutoReverse(true);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
	}
//	
//	
//	public void move(Point2D originalVect, Point2D newVect) {
//		if(originalVect.angle(newVect)!=null)
//			
//	}
//	
	

}
