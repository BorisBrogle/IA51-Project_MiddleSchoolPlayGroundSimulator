package fr.utbm.ia51.graph.human;

import java.util.UUID;

import fr.utbm.ia51.Globals;
import fr.utbm.ia51.activities.ActivityToolTip;
import fr.utbm.ia51.activities.ActivityType;
import fr.utbm.ia51.graph.environment.EnvironmentEntity;
import fr.utbm.ia51.graph.environment.GraphEnvironment;
import fr.utbm.ia51.tools.Point2f;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import tools.Arrow;

/**
 * @author Arthur
 *
 */
public class GraphHuman extends EnvironmentEntity {
	
	private String name;
	private GraphEnvironment environment;
	private StackPane humanBody;
	private Circle head = new Circle();
	private Circle lefteye, righteye;
	private Label coordinatesLabel;
	private UUID uuid;
	private ActivityToolTip activityDesired;
	private Rectangle viewField;
	private Rectangle collisionBox;
	

	
	private Arrow forceArrow;
	
	//Add position 
	
	
	public GraphHuman (int x,int y, double radius, String name, GraphEnvironment environment) {
		super(null);
		this.setVisible(false);
		this.setManaged(true);
		
		
		this.environment = environment;
		this.setTranslateX(x);
		this.setTranslateY(y);
		this.head.setCenterX(x);
		this.head.setCenterY(y);
		this.head.setRadius(radius);
		this.head.setFill(Color.NAVAJOWHITE);
		this.name = name;
		
		
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
		this.viewField.setVisible(false);
		
		
		this.collisionBox = new Rectangle(radius*2, radius*2);
		this.collisionBox.setMouseTransparent(true);
		this.collisionBox.setFill(Color.TRANSPARENT);
		

		
		
		
		
		
		this.coordinatesLabel=new Label();
		this.coordinatesLabel.setFont(new Font(radius*0.8));
		this.coordinatesLabel.setTranslateY(-this.head.getRadius()*-2);
		this.coordinatesLabel.setVisible(false);


		
		activityDesired = new ActivityToolTip(ActivityType.BASKETBALL);
		this.activityDesired.setVisible(false);
		
		
		// Arrow representing the force vector of the agent
		this.forceArrow = new Arrow();
		this.forceArrow.setStartX(x);
		this.forceArrow.setStartY(y);
		this.forceArrow.setEndX(x+25);
		this.forceArrow.setEndY(y+25);
		this.forceArrow.setVisible(false);
		


		
		this.humanBody = new StackPane();
		this.humanBody.getChildren().addAll(head,lefteye,righteye);
		this.getChildren().addAll(this.viewField, this.coordinatesLabel,this.collisionBox, this.humanBody, this.activityDesired, this.forceArrow);
		
	}
	
	

	
	
	/**
	 * Translate the graphical representation of an agent
	 * @param x
	 * @param y
	 * @param speed
	 */
	public void moveTo(double x, double y, double speed) {
		try {
			//Let time to the GUI for refreshing
			if(Globals.NB_AGENTS>50) {
				Thread.sleep((long) 0.2);
				
			}else
			if(Globals.NB_AGENTS>20) {
				Thread.sleep(1);
			}else {
				Thread.sleep(5%Globals.NB_AGENTS);
			}
//			Thread.sleep(2+Globals.NB_AGENTS/);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Platform.runLater(()->{
			this.setTranslateX(x-this.viewField.getWidth()/2);
			this.setTranslateY(y-this.viewField.getHeight()/2);
			this.coordinatesLabel.setText("x="+(int)this.getTranslateX()+"y="+(int)this.getTranslateY());
		});
		
		
		
	}
	
	
	
	
	/*
	 * Used to update the force arrow of the agent, representing the direction it is following.
	 */
	public void updateForceVector(double endX, double endY) {
		double x = this.getX();
		double y = this.getY();
		this.forceArrow.setStartX(x);
		this.forceArrow.setStartY(y);
		this.forceArrow.setEndX(x+endX*20);
		this.forceArrow.setEndY(y+endY*20);
	}
	
	
	/**
	 * Used to modify the aspect of a student when it enters in a covered area (like forest or buildings) 
	 * @param hide
	 */
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
	
	
	
	
	
	
	/**
	 *Those fonctions override the EnvironmentEntity's function to get the collision area according to a rectangle surrounding the student head
	 */
	@Override
	public double getMinX() {

		return this.getBoundsInParent().getMinX()+this.collisionBox.getBoundsInParent().getMinX();
	}
	
	@Override
	public double getMinY() {
		return this.getBoundsInParent().getMinY()+this.collisionBox.getBoundsInParent().getMinY();
	}
	
	@Override
	public double getMaxX(){
		return this.getBoundsInParent().getMaxX()-this.collisionBox.getBoundsInParent().getMinX();
	}
	
	@Override
	public double getMaxY() {
		return this.getBoundsInParent().getMaxY()-this.collisionBox.getBoundsInParent().getMinY();
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
		double xmin = this.getBoundsInParent().getMinX()+this.collisionBox.getBoundsInParent().getMinX();
		double ymin = this.getBoundsInParent().getMinY()+this.collisionBox.getBoundsInParent().getMinY();
		
		
		double xmax = this.getBoundsInParent().getMaxX()-this.collisionBox.getBoundsInParent().getMinX();
		double ymax = this.getBoundsInParent().getMaxY()-this.collisionBox.getBoundsInParent().getMinY();

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





	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}
	

	


}
