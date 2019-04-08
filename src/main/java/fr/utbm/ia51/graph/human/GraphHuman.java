package fr.utbm.ia51.graph.human;

import fr.utbm.ia51.graph.environment.GraphEnvironment;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventType;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class GraphHuman extends StackPane {
	
	
	private GraphEnvironment environment;
	private Rectangle arms;
	private Circle head = new Circle();
	private Circle selectionCircle = new Circle();
	private Circle lefteye, righteye;
	private Label nameLabel;
	private SimpleBooleanProperty isSelected = new SimpleBooleanProperty(false);
	private GraphInformationWindow infoWindow;
	
	//Add position 
	
	
	
	public GraphHuman (int x,int y, String headStyle, String armStyle, double sizeRatioHead,String name, GraphEnvironment environment) {

		super();
		this.environment = environment;
		this.setTranslateX(x);
		this.setTranslateY(y);
		this.head.setCenterX(x);
		this.head.setCenterY(y);
		this.head.setRadius(6*sizeRatioHead);
		this.head.setFill(Color.NAVAJOWHITE);
		
		
		this.lefteye = new Circle(sizeRatioHead);
		this.righteye = new Circle(sizeRatioHead);
		
		
		// Binding of eye's position according to the head position
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
	
		
		//Management of information view when clicking on the human head
		
		this.infoWindow = new GraphInformationWindow("Students", "Gudule", "2", "Good Goat");
		
		this.selectionCircle.setRadius(this.head.getRadius()+2);
		this.selectionCircle.setFill(Color.YELLOW);
		this.selectionCircle.setVisible(false);
		
		
		
		HBox infoBox = new HBox();
		infoBox.setAlignment(Pos.BOTTOM_LEFT);
		infoBox.getChildren().add(this.infoWindow);
		this.infoWindow.visibleProperty().bind(isSelected);
		this.selectionCircle.visibleProperty().bind(isSelected);
		this.environment.getChildren().add(infoBox);



		
		
		this.head.setOnMouseClicked(e->{
			if(this.isSelected.getValue() == true) 
				this.isSelected.set(false);
			else 
				this.isSelected.set(true);
		});
		
		this.addEventFilter(KeyEvent.ANY, e->{
			if(e.getCode().equals(KeyCode.ESCAPE))
				isSelected.set(false);
		});
		
		this.getChildren().addAll(arms,selectionCircle,head,lefteye,righteye);
		
	}
	
	
	public RotateTransition rotateGraph(double angle,double speed) {
		RotateTransition rotation = new RotateTransition(Duration.millis(1000/speed));
		rotation.setFromAngle(this.head.getRotate());
		rotation.setToAngle(angle);
		return rotation;
	}
	
	public TranslateTransition translateGraph(double x, double y, double speed) {
		
		TranslateTransition translation = new TranslateTransition(Duration.millis((3000/speed)));
		translation.setFromX(this.head.getCenterX());
		translation.setFromY(this.head.getCenterY());
		translation.setToX(x);
		translation.setToY(y);
		
		return translation;
	}
	
	
	public void moveTo(double x,double y, double speed) {
		
		Point2D p1;
		Point2D p2;
		Point2D p3;
		Point2D p4;

		p1 = new Point2D(this.head.getCenterX(), this.head.getCenterY());
		p2 = new Point2D(x, y);

		p3 = p2.subtract(p1);
		p4 = new Point2D(1, 0);
		
		SequentialTransition sequentialTransition = new SequentialTransition();
		sequentialTransition.getChildren().addAll(rotateGraph(p4.angle(p3), speed),translateGraph(x, y, speed));
		sequentialTransition.setNode(this);
		sequentialTransition.play();
		

		System.out.println( "Point2D: " + p4.angle(p3));
		
	}
//	
//	
//	public void move(Point2D originalVect, Point2D newVect) {
//		if(originalVect.angle(newVect)!=null)
//			
//	}
//	
	

}
