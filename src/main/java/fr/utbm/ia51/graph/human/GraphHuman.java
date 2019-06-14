package fr.utbm.ia51.graph.human;

import java.util.UUID;

import fr.utbm.ia51.Globals;
import fr.utbm.ia51.activities.ActivityToolTip;
import fr.utbm.ia51.activities.ActivityType;
import fr.utbm.ia51.graph.environment.EnvironmentEntity;
import fr.utbm.ia51.graph.environment.GraphEnvironment;
import fr.utbm.ia51.tools.Point2f;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
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
import sun.misc.GC;
import tools.Arrow;

public class GraphHuman extends EnvironmentEntity {
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
	private Rectangle collisionBox;
	

	
	private Arrow forceArrow;
	
	//Add position 
	
	
	public GraphHuman (int x,int y, String headStyle, String armStyle, double radius, String name, GraphEnvironment environment) {
		super(null);
		this.setManaged(true);

		this.setStyle("-fx-border-color : red");

		this.environment = environment;
		this.setTranslateX(x);
		this.setTranslateY(y);
		this.head.setCenterX(x);
		this.head.setCenterY(y);
		this.head.setRadius(radius);
		this.head.setFill(Color.NAVAJOWHITE);
		
		
		this.uuid = UUID.randomUUID();

		double eyeRadius = (1.0/6.0)*radius;
		this.lefteye = new Circle(eyeRadius);
		this.righteye = new Circle(eyeRadius);
		
		// Binding of eye's position according to the head position
		this.lefteye.translateXProperty().bind(head.translateXProperty().subtract(3*eyeRadius));
		this.righteye.translateXProperty().bind(head.translateXProperty().add(3*eyeRadius));
		this.lefteye.translateYProperty().bind(head.translateYProperty().add(3*eyeRadius));
		this.righteye.translateYProperty().bind(head.translateXProperty().add(3*eyeRadius));
			
		this.viewField = new Rectangle(head.getRadius()*8, head.getRadius()*8);
		this.viewField.setFill(new Color(0,1,0,0.50));
//		this.arms = new Rectangle(x-4,y-4,18*sizeRatioHead,6*sizeRatioHead);
//		this.arms.setFill(Color.GREEN);
		this.viewField.setVisible(false);
		
		
		this.collisionBox = new Rectangle(radius*2, radius*2);
		this.collisionBox.setMouseTransparent(true);
		this.collisionBox.setFill(Color.TRANSPARENT);
		this.collisionBox.setStyle("-fx-border-color : blue");
		
		
		
		
		
		this.coordinatesLabel=new Label();
		
//		this.coordinatesLabel.textProperty().bind(Bindings.createStringBinding(
//				()->"x="+this.boundsInParentProperty().getValue().getMaxX()/2+" y="+this.boundsInParentProperty().getValue().getMaxY()/2,this.boundsInParentProperty(),this.boundsInParentProperty()));
		this.coordinatesLabel.setFont(new Font(radius*0.8));
		this.coordinatesLabel.setTranslateY(-this.head.getRadius()*-2);
		this.coordinatesLabel.setVisible(false);
	
		
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
		this.environment.getArtifacts().add(this);
		
		this.head.setOnMouseClicked(e->{
			System.out.println();
			if(this.isSelected.getValue() == true) 
				this.isSelected.set(false);
			else 
				this.isSelected.set(true);
		});
		
		this.addEventFilter(KeyEvent.ANY, e->{
			if(e.getCode().equals(KeyCode.ESCAPE))
				isSelected.set(false);
		});
		
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
		this.forceArrow.setVisible(false);
		
		
		this.humanBody = new StackPane();
		
		this.humanBody.getChildren().addAll(selectionCircle,head,lefteye,righteye);
		this.getChildren().addAll(this.viewField, this.coordinatesLabel,this.collisionBox, this.humanBody, this.activityDesired, this.forceArrow);
		
	}
	
	

	
	
	public void moveTo(double x, double y, double speed) {
//		this.head.setTranslateX(x);
//		this.head.setTranslateY(y);
//		this.setTranslateX(x-this.viewField.getWidth()/2);
//		this.setTranslateY(y-this.viewField.getHeight()/2);
//		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		this.setTranslateX(x-this.viewField.getWidth()/2);
//		this.setTranslateY(y-this.viewField.getHeight()/2);
		Platform.runLater(()->{
			this.setTranslateX(x-this.viewField.getWidth()/2);
			this.setTranslateY(y-this.viewField.getHeight()/2);
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
	
	
	public void sethideInCoveredArea(boolean hide) {
		if(hide) {
			this.lefteye.setVisible(false);
			this.righteye.setVisible(false);
			this.head.setStyle("-fx-fill : transparent ;-fx-stroke : yellow;  -fx-stroke-dash-array: 5; -fx-stroke-width : 2 ");			
		}else {
			this.lefteye.setVisible(true);
			this.righteye.setVisible(true);
			this.head.setStyle("-fx-fill : NAVAJOWHITE ;-fx-stroke : yellow;  -fx-stroke-dash-array: 5; -fx-stroke-width : 0 ");	
			
		}
	}
	
	
	public void setActivity(ActivityType activity) {
		this.activityDesired.changeDesire(activity);
	}
	
	
	@Override
	public double getMinX() {
		System.out.println("CollisionMinX :"+this.collisionBox.getBoundsInParent().getMinX());
		return this.getBoundsInParent().getMinX()+(this.collisionBox.getBoundsInParent().getMinX()-this.getBoundsInParent().getMinX());
	}
	
	@Override
	public double getMinY() {
		return this.getBoundsInParent().getMinY()+this.collisionBox.getBoundsInParent().getMinY();
	}
	
	@Override
	public double getMaxX(){
		return this.getBoundsInParent().getMaxX()-this.collisionBox.getBoundsInParent().getMaxX();
	}
	
	@Override
	public double getMaxY() {
		return this.getBoundsInParent().getMaxY()-this.collisionBox.getBoundsInParent().getMaxY();
	}
	
	@Override
	public double getEnvironmentEntityHeight() {
		return this.collisionBox.getHeight();
	}
	
	@Override
	public double getEnvironmentEntityWidth() {
		return this.collisionBox.getWidth();
	}
	
	
	@Override
	public Point2f getNearestPointInEntity(Point2f point) {
		double xmin = this.getBoundsInParent().getMinX()+(this.collisionBox.getBoundsInParent().getMinX()-this.getBoundsInParent().getMinX());
		double ymin = this.getBoundsInParent().getMinY()+(this.collisionBox.getBoundsInParent().getMinY()-this.getBoundsInParent().getMinY());
		double xmax = this.getBoundsInParent().getMaxX()+(this.collisionBox.getBoundsInParent().getMaxX()-this.getBoundsInParent().getMaxX());
		double ymax = this.getBoundsInParent().getMaxY()+(this.collisionBox.getBoundsInParent().getMaxY()-this.getBoundsInParent().getMaxY());

		double x = (double)point.getX();
		double y = (double)point.getY();

		if(x<=xmin) {
			if(y<=ymin)
				return new Point2f(xmin,ymin);
			if(y>=ymax)
				return new Point2f(xmin,ymax);
			return projete(x,y,xmin,ymin,xmin,ymax);
		}
		if(x>=xmax) {
			if(y<=ymin)
				return new Point2f(xmax,ymin);
			if(y>=ymax)
				return new Point2f(xmax,ymax);
			return projete(x,y,xmax,ymin,xmax,ymax);

		}
		if(y<=ymin)
			return projete(x,y,xmin,ymin,xmax,ymin);
		return projete(x,y,xmin,ymax,xmax,ymax);
				
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




	public ActivityToolTip getActivityDesired() {
		return activityDesired;
	}




	public void setActivityDesired(ActivityToolTip activityDesired) {
		this.activityDesired = activityDesired;
	}




	public Label getCoordinatesLabel() {
		return coordinatesLabel;
	}




	public Arrow getForceArrow() {
		return forceArrow;
	}
	
	
	
	/*public double getDistance() {
		return this.distance;
	}
	
	public void setDistance(double d) {
		this.distance = d;
	}*/
//	
//	
//	public void move(Point2D originalVect, Point2D newVect) {
//		if(originalVect.angle(newVect)!=null)
//			
//	}
//	
	


}
