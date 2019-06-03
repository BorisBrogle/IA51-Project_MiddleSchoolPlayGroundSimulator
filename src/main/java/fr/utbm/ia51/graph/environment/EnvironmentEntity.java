package fr.utbm.ia51.graph.environment;

import fr.utbm.ia51.GUI;
import fr.utbm.ia51.tools.Point2f;
import fr.utbm.ia51.tools.PseudoRandomGenerator;
import javafx.scene.layout.StackPane;

public class EnvironmentEntity extends StackPane {
	
	public EnvironmentEntity() {
		super();
	}
	
	public Point2f getNearestPointInEntity(Point2f point) {
		double xmin = this.getBoundsInLocal().getMinX();
		double ymin = this.getBoundsInLocal().getMinY();
		double xmax = this.getBoundsInLocal().getMaxX();
		double ymax = this.getBoundsInLocal().getMaxY();
		
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
	
	
	public Point2f projete(double x, double y, double xa, double ya, double xb,double yb) {
		return new Point2f((xa + (((x-xa)*(xb-xa)+(y-ya)*(yb-ya))/((xb-xa)*(xb-xa)+(yb-ya)*(yb-ya)))*(xb-xa))
				,(ya + (((x-xa)*(xb-xa)+(y-ya)*(yb-ya))/((xb-xa)*(xb-xa)+(yb-ya)*(yb-ya)))*(yb-ya)));
	}
	
	
	public Point2f getRandomPointInEntity() {
		
		 double newX = Math.random() *((this.getBoundsInLocal().getMaxX()-this.getBoundsInLocal().getMinX())) + this.getBoundsInLocal().getMinX();
		 double newY = Math.random() *((this.getBoundsInLocal().getMaxY()-this.getBoundsInLocal().getMinY())) + this.getBoundsInLocal().getMinY();
				 
		return new Point2f(newX,newY);
		
		
	}

}
