package fr.utbm.ia51.graph.environment;

import java.util.ArrayList;

import fr.utbm.ia51.activities.ActivityType;
import fr.utbm.ia51.graph.human.GraphHuman;
import fr.utbm.ia51.tools.Point2f;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class GraphEnvironment extends StackPane {
	
	
	private ArrayList<EnvironmentEntity> artifacts = new ArrayList<>();
	private ArrayList<EnvironmentEntity> coveredAreas = new ArrayList<>();
	
	
	
	private SoccerField soccerField;
	private Bench bench;
	private BasketCourt basketCourt;
	private Forest forest;
	private TennisTable tennisTable;
	private Library library;
	private Toilet toilet;
	private ClassLine classline;
	
	private Button ringButton;

	
	private double wFactor;
	private double hFactor;
	private double width;
	private double height;
	
	public GraphEnvironment(double width, double height) {
		super();
		this.setPrefSize(width, height);
		this.setStyle("-fx-border-color : blue");
		
		// All the environment elements are defined by their 1600:900 coordinates
		this.wFactor = width/1600; // Width factor to multiply the width coordinate
		this.hFactor = height/900; // Height factor to multiply the height coordinate
		
		this.width = width;
		this.height = height;

		
		// You should use the method add this way => this.add(object, width, height, xPosition, yPosition)
		
		// Forest 
		this.forest = new Forest("file:src/main/resources/graphism/image/forest.png");
		this.add(this.forest, 549, 432, 1050, 0);

		// Basket court
		this.basketCourt = new BasketCourt("file:src/main/resources/graphism/image/basketcourt.png");
		this.add(this.basketCourt, 257, 369, 730, 0);

		// Soccer field
		this.soccerField = new SoccerField("file:src/main/resources/graphism/image/soccerfield.png");
		this.add(this.soccerField, 572, 320, 1024, 576);

		// Library
		this.library = new Library("file:src/main/resources/graphism/image/library.png");
		this.add(this.library, 495, 220, 492, 680);

		// Toilet
		this.toilet = new Toilet("file:src/main/resources/graphism/image/toilets.png");
		this.add(this.toilet, 218, 127, 53, 0);

		// Class lines
		this.classline = new ClassLine("file:src/main/resources/graphism/image/classline.png");
		this.add(this.classline, 112, 208, 27, 422);

		// Tennis tables
		this.tennisTable = new TennisTable("file:src/main/resources/graphism/image/tabletennisarea.png");
		this.add(this.tennisTable, 342, 57, 63, 806);

		// Bench
		this.bench = new Bench("file:src/main/resources/graphism/image/bencharea.png");
		this.add(this.bench, 305, 26, 353, 17);
		
		
		
		this.ringButton = new Button();
		ImageView image = new ImageView(new Image("file:src/main/resources/graphism/image/bell.png"));
		image.setPreserveRatio(false);
		image.setFitHeight(40);
		image.setFitWidth(40);
		this.ringButton.setGraphic(image);
		

	}
	
	
	public void add(EnvironmentEntity e, double w, double h, double x, double y) {
		w *= wFactor;
		h *= hFactor;
		x *= wFactor;
		y *= hFactor;
		
		e.init(w, h);
		e.setTranslateX(-this.width/2.0 + x + w/2.0);
		e.setTranslateY(-this.height/2.0 + y + h/2.0);
		
		this.getChildren().addAll(e);
		this.artifacts.add(e);
		if(e.isCovering)
			this.coveredAreas.add(e);
	}
	
	
	public void printAllArtifactsPositions() {
		for(StackPane s : this.artifacts)
			System.out.println("Artifact "+s.getClass().getName()+" x= "+s.getTranslateX()+" y= "+s.getTranslateY());
	}
	
	
	public ArrayList<EnvironmentEntity> getIntersectedArtifacts(GraphHuman graphHuman) {
		ArrayList<EnvironmentEntity> intersectArtifacts = new ArrayList<>();
		for(EnvironmentEntity s : this.artifacts) {
			if(s.getBoundsInParent().intersects(graphHuman.getBoundsInParent()) && !s.contains(new Point2D(graphHuman.getCenterPoint().getX(),graphHuman.getCenterPoint().getY()))) {
				//System.out.println("Intersect with "+s.getClass().getName());
				intersectArtifacts.add(s);
			}
		}
		return intersectArtifacts;
		
	}
	
	
	public EnvironmentEntity getArtifactFromActivity(ActivityType activityType) {
		switch(activityType) {
			case BASKETBALL: return this.basketCourt;
			case SOCCER : return this.soccerField;
			case BENCH : return this.bench;
			case TOILET : return this.toilet;
			case FOREST: return this.forest;
			case CLASSLINE : return this.classline;
			case LIBRARY : return this.library;
			case TENNISTABLE : return this.tennisTable;
			case FIGHT : return this.toilet; //C'EST JUSTE POUR EVITER QUE CA PLANTE FAUDRA BIEN SUR CHANGER
		default:
			 return this.toilet;
		}
	}
	
	
	public boolean isInCoveredArea(Point2f position) {
		for(EnvironmentEntity e : this.coveredAreas)
			if(e.getBoundsInParent().contains(new Point2D(position.getX(),position.getY())))
				return true;
		return false;
	}

	
	public Button getRingButton() {
		return ringButton;
	}

	
	public void setRingButton(Button ringButton) {
		this.ringButton = ringButton;
	}


	public ArrayList<EnvironmentEntity> getArtifacts() {
		return artifacts;
	}
	
}
