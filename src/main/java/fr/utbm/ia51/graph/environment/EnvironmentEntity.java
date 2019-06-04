package fr.utbm.ia51.graph.environment;

import fr.utbm.ia51.GUI;
import fr.utbm.ia51.tools.Point2f;
import fr.utbm.ia51.tools.Vector2f;
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


	public Vector2f getNormalVectorAtPoint(Point2f applicationPoint) {


		//Le terme previous et next n'a pas vraiment une grande importance, c'est juste les points voisins à 1 près dans la figure
		Point2f previousPoint=new Point2f(-1.0,-1.0);
		Point2f nextPoint = new Point2f(-1.0,-1.0);



		//Cas du coin en haut à gauche

		if(applicationPoint.getX()==this.getBoundsInLocal().getMinX()&&applicationPoint.getY()==this.getBoundsInLocal().getMinY()) {
			previousPoint = new Point2f(applicationPoint.getX(),applicationPoint.getY()-1);
			nextPoint = new Point2f(applicationPoint.getX()+1,applicationPoint.getY());
		}
		//Cas du coin en haut à droite

		if(applicationPoint.getX()==this.getBoundsInLocal().getMaxX()&&applicationPoint.getY()==this.getBoundsInLocal().getMinY()) {
			previousPoint = new Point2f(applicationPoint.getX()-1,applicationPoint.getY());
			nextPoint = new Point2f(applicationPoint.getX(),applicationPoint.getY()+1);
		}

		//Cas du coin en bas à droite
		if(applicationPoint.getX()==this.getBoundsInLocal().getMaxX()&&applicationPoint.getY()==this.getBoundsInLocal().getMaxY()) {
			previousPoint = new Point2f(applicationPoint.getX(),applicationPoint.getY()-1);
			nextPoint = new Point2f(applicationPoint.getX()-1,applicationPoint.getY());
		}

		//Cas du coin en bas à gauche
		if(applicationPoint.getX()==this.getBoundsInLocal().getMinX()&&applicationPoint.getY()==this.getBoundsInLocal().getMaxY()) {
			previousPoint = new Point2f(applicationPoint.getX()+1,applicationPoint.getY());
			nextPoint = new Point2f(applicationPoint.getX(),applicationPoint.getY()-1);
		}




		if(previousPoint.getX()==-1.0) {
			//Présence du point d'application sur un des côtés verticaux
			if(applicationPoint.getX()==this.getBoundsInLocal().getMinX() || applicationPoint.getX()==this.getBoundsInLocal().getMaxX()) {
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