package fr.utbm.ia51.graph.human;

import java.util.UUID;

import fr.utbm.ia51.Globals;
import fr.utbm.ia51.activities.ActivityToolTip;
import fr.utbm.ia51.activities.ActivityType;
import fr.utbm.ia51.graph.environment.GraphEnvironment;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import tools.Arrow;

public class GraphHuman extends StackPane {
	private GraphEnvironment environment;
	private StackPane humanBody;
	//private Rectangle arms;
	private Circle head = new Circle();
	private Circle selectionCircle = new Circle();
	private Circle lefteye, righteye;
	private Label coordinatesLabel;
	private SimpleBooleanProperty isSelected = new SimpleBooleanProperty(false);
	private GraphInformationWindow infoWindow;
	private UUID uuid;
	private ActivityToolTip activityDesired;
	private Rectangle viewField;
	private Arrow forceArrow;
	
	//Add position 
	
	
	public GraphHuman (int x,int y, String headStyle, String armStyle, double sizeRatioHead, String name, GraphEnvironment environment) {
		super();
		this.setStyle("-fx-border-color : red");
		this.setManaged(true);
		
		this.environment = environment;
		this.setTranslateX(x);
		this.setTranslateY(y);
		this.head.setCenterX(x);
		this.head.setCenterY(y);
		this.head.setRadius(6*sizeRatioHead);
		this.head.setFill(Color.NAVAJOWHITE);
		this.uuid = UUID.randomUUID();
		
		this.lefteye = new Circle(sizeRatioHead);
		this.righteye = new Circle(sizeRatioHead);
		
		// Binding of eye's position according to the head position
		this.lefteye.translateXProperty().bind(head.translateXProperty().subtract(3*sizeRatioHead));
		this.righteye.translateXProperty().bind(head.translateXProperty().add(3*sizeRatioHead));
		this.lefteye.translateYProperty().bind(head.translateYProperty().add(3*sizeRatioHead));
		this.righteye.translateYProperty().bind(head.translateXProperty().add(3*sizeRatioHead));
			
		this.viewField = new Rectangle(head.getRadius()*8, head.getRadius()*8);
		this.viewField.setFill(new Color(0,1,0,0.50));
//		this.arms = new Rectangle(x-4,y-4,18*sizeRatioHead,6*sizeRatioHead);
//		this.arms.setFill(Color.GREEN);
		
		this.coordinatesLabel=new Label();
		
//		this.coordinatesLabel.textProperty().bind(Bindings.createStringBinding(
//				()->"x="+this.boundsInParentProperty().getValue().getMaxX()/2+" y="+this.boundsInParentProperty().getValue().getMaxY()/2,this.boundsInParentProperty(),this.boundsInParentProperty()));
		this.coordinatesLabel.setFont(new Font(sizeRatioHead*5));
		this.coordinatesLabel.setTranslateY(this.head.getRadius()*-2);
	
		
		//Management of information view when clicking on the human head
		
		//TODO Faire la récupération des données depuis la classe élève et non pas en dur comme codé actuellement 
		
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
		
		//Infobox above the child
		activityDesired = new ActivityToolTip(ActivityType.BASKETBALL);
		this.activityDesired.rotateProperty().bind(this.rotateProperty().multiply(-1));
		this.activityDesired.setTranslateX(5);
		this.activityDesired.setTranslateY(-30);
		this.activityDesired.setVisible(false);
		
		
		// Arrow representing the force vector of the agent
		this.forceArrow = new Arrow();

		this.forceArrow.setStartX(x);
		this.forceArrow.setStartY(y);
		this.forceArrow.setEndX(x+25);
		this.forceArrow.setEndY(y+25);
		if(!Globals.SHOW_FORCE_VECTOR) {
			this.forceArrow.setVisible(false);
		}
		
		
		this.humanBody = new StackPane();
//		this.humanBody.getChildren().addAll(arms,selectionCircle,head,lefteye,righteye);
		this.humanBody.getChildren().addAll(selectionCircle,head,lefteye,righteye);
		this.getChildren().addAll(this.viewField,this.coordinatesLabel, this.humanBody, activityDesired, this.forceArrow);
//		this.setStyle("-fx-border-color : blue");
		//this.environment.getChildren().add(activityDesired);
		
	}
	
	

	
	public void moveTo(double x, double y, double speed) {
//		this.head.setTranslateX(x);
//		this.head.setTranslateY(y);
		this.setTranslateX(x-this.viewField.getWidth()/2);
		this.setTranslateY(y-this.viewField.getHeight()/2);
		
		
		Platform.runLater(()->{
			this.coordinatesLabel.setText("x="+(int)this.getTranslateX()+"y="+(int)this.getTranslateY());
		});
	}
	
	
	/*
	 * Used to show the force arrow of the agent, representing the direction it is following.
	 */
	public void showForce(double endX, double endY) {
		double x = this.getX();
		double y = this.getY();
		this.forceArrow.setStartX(x);
		this.forceArrow.setStartY(y);
		this.forceArrow.setEndX(x+endX*20);
		this.forceArrow.setEndY(y+endY*20);
	}


	public UUID getUuid() {
		return uuid;
	}


	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	
	public double getX() {
		return (this.getBoundsInParent().getMaxX() - (this.getBoundsInParent().getMaxX() - this.getBoundsInParent().getMinX())/2);
	}
	
	public double getY() {
		return (this.getBoundsInParent().getMaxY() - (this.getBoundsInParent().getMaxY() - this.getBoundsInParent().getMinY())/2);
	}
	
	public Rectangle getViewField() {
		return this.viewField; 	
	}
	
	public GraphEnvironment getGraphEnvironment(){
		return this.environment;
	}


	public GraphEnvironment getEnvironment() {
		return environment;
	}


	public void setEnvironment(GraphEnvironment environment) {
		this.environment = environment;
	}
}
