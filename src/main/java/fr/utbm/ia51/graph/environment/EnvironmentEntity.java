package fr.utbm.ia51.graph.environment;

import fr.utbm.ia51.Globals;
import fr.utbm.ia51.activities.ActivityType;
import fr.utbm.ia51.graph.human.GraphHuman;
import fr.utbm.ia51.tools.Point2f;
import fr.utbm.ia51.tools.Vector2f;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

/**
 * @author Arthur
 * Represents any artifacts of the environment but also the students representations, use to compute collision between agents and playfields 
 */
public class EnvironmentEntity extends StackPane {
	protected ActivityType activityType;
	private String imagePath;
	protected boolean isCovering = false;

	public EnvironmentEntity(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
	public Rectangle getArea() {
		return new Rectangle(this.getTranslateX(), this.getTranslateY(), this.getWidth(), this.getHeight());
	}
	
	
	public boolean intersect(GraphHuman g) {
		if(this.getArea().intersects(g.getLayoutBounds()))
			return true;
		return false;
	}
	
	
	public void init(double width, double height) {
		if(this.imagePath!=null) {
		Image image = new Image(this.imagePath);
		ImageView imageView = new ImageView();
		
		imageView.setImage(image);
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);

        this.setMaxSize(imageView.getFitWidth(), imageView.getFitHeight());
        this.getChildren().addAll(imageView);
		}
	}
	

	/**
	 * Used to perform the repulsion, repulsion vector will be computed by taking the nearest point in an artifact according to the agent position as vector's start, and the agent's position as vector's end 
	 * @param point : position of an agent 
	 * @return
	 */
	public Point2f getNearestPointInEntity(Point2f point) {
		double xmin = this.getBoundsInParent().getMinX();
		double ymin = this.getBoundsInParent().getMinY();
		double xmax = this.getBoundsInParent().getMaxX();
		double ymax = this.getBoundsInParent().getMaxY();

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
	
	
	public Point2f getCenterPoint() {
		double xmin = this.getBoundsInParent().getMinX();
		double ymin = this.getBoundsInParent().getMinY();
		double xmax = this.getBoundsInParent().getMaxX();
		double ymax = this.getBoundsInParent().getMaxY();
		
		return new Point2f(xmin+((xmax-xmin)/2),ymin+((ymax-ymin)/2));
	}


	
	/**
	 * Not used, made to compute a normal vector of a point by taking the cross product of vectors computed from applicationPoint's neighbor 
	 * @param applicationPoint 
	 * @return normal vector of a side of an artifact
	 */
	public Vector2f getNormalVectorAtPoint(Point2f applicationPoint) {
		Point2f previousPoint=new Point2f(-1.0,-1.0);
		Point2f nextPoint = new Point2f(-1.0,-1.0);

		if(applicationPoint.getX()==this.getBoundsInParent().getMinX()&&applicationPoint.getY()==this.getBoundsInParent().getMinY()) {
			previousPoint = new Point2f(applicationPoint.getX(),applicationPoint.getY()-1);
			nextPoint = new Point2f(applicationPoint.getX()+1,applicationPoint.getY());
		}
		
		if(applicationPoint.getX()==this.getBoundsInParent().getMaxX()&&applicationPoint.getY()==this.getBoundsInParent().getMinY()) {
			previousPoint = new Point2f(applicationPoint.getX()-1,applicationPoint.getY());
			nextPoint = new Point2f(applicationPoint.getX(),applicationPoint.getY()+1);
		}

		if(applicationPoint.getX()==this.getBoundsInParent().getMaxX()&&applicationPoint.getY()==this.getBoundsInParent().getMaxY()) {
			previousPoint = new Point2f(applicationPoint.getX(),applicationPoint.getY()-1);
			nextPoint = new Point2f(applicationPoint.getX()-1,applicationPoint.getY());
		}

		if(applicationPoint.getX()==this.getBoundsInParent().getMinX()&&applicationPoint.getY()==this.getBoundsInParent().getMaxY()) {
			previousPoint = new Point2f(applicationPoint.getX()+1,applicationPoint.getY());
			nextPoint = new Point2f(applicationPoint.getX(),applicationPoint.getY()-1);
		}

		if(previousPoint.getX()==-1.0) {
			if(applicationPoint.getX()==this.getBoundsInParent().getMinX() || applicationPoint.getX()==this.getBoundsInParent().getMaxX()) {
					previousPoint = new Point2f(applicationPoint.getX(),applicationPoint.getY()+1);
					nextPoint = new Point2f(applicationPoint.getX(),applicationPoint.getY()-1);
			}else {
					previousPoint = new Point2f(applicationPoint.getX()+1,applicationPoint.getY());
					nextPoint = new Point2f(applicationPoint.getX()-1,applicationPoint.getY());
			}

		}
		Vector2f vect1 = new Vector2f(previousPoint.getX()-applicationPoint.getX(),previousPoint.getY()-applicationPoint.getY());
		Vector2f vect2 = new Vector2f(nextPoint.getX()-applicationPoint.getX(),nextPoint.getY()-applicationPoint.getY());

		return new Vector2f(vect1.getX()*vect2.getY()-vect1.getY()*vect2.getX(), vect1.getY()*vect2.getX()-vect1.getX()*vect2.getY());
	}

	
	/**
	 * Compute the orthogonal projection of a point(x,y) on a segment AB((xa,ya),(xb,yb))
	 * @param x
	 * @param y
	 * @param xa
	 * @param ya
	 * @param xb
	 * @param yb
	 * @return
	 */
	public Point2f projete(double x, double y, double xa, double ya, double xb,double yb) {
		return new Point2f((xa + (((x-xa)*(xb-xa)+(y-ya)*(yb-ya))/((xb-xa)*(xb-xa)+(yb-ya)*(yb-ya)))*(xb-xa))
				,(ya + (((x-xa)*(xb-xa)+(y-ya)*(yb-ya))/((xb-xa)*(xb-xa)+(yb-ya)*(yb-ya)))*(yb-ya)));
	}
	
	
	

	/**
	 * @return a random point in an artifact, used to get target coordinates for an agent
	 */
	public Point2f getRandomPointInEntity() {
		// We make sure that the entity is wide and tall enough for the agents
		double xMax = this.getBoundsInParent().getMaxX()-2.0*Globals.AGENT_RADIUS;
		if(xMax < 1) {
			xMax = 1;
		}
		double yMax = this.getBoundsInParent().getMaxY()-2.0*Globals.AGENT_RADIUS;
		if(yMax < 1) {
			yMax = 1;
		}
		
		
		double newX = Globals.randomGenerator.getRandom(this.getBoundsInParent().getMinX(), xMax);
		double newY = Globals.randomGenerator.getRandom(this.getBoundsInParent().getMinY(), yMax);

		return new Point2f(newX,newY);
	}
	
	
	public double getMinX() {
		return this.getBoundsInParent().getMinX();
	}
	
	public double getMinY() {
		return this.getBoundsInParent().getMinY();
	}
	
	public double getMaxX(){
		return this.getBoundsInParent().getMaxX();
	}
	
	public double getMaxY() {
		return this.getBoundsInParent().getMaxY();
	}
	
	public double getEnvironmentEntityHeight() {
		return this.getBoundsInParent().getHeight();
	}
	
	public double getEnvironmentEntityWidth() {
		return this.getBoundsInParent().getWidth();
	}
	
	public Bounds getBoundsOfEntity() {
		return this.getBoundsInParent();
	}
	
	public ActivityType getActivityType() {
		return this.activityType;
	}

}
