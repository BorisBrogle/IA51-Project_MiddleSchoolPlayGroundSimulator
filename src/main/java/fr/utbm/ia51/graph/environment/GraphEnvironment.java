package fr.utbm.ia51.graph.environment;

import java.util.ArrayList;

import fr.utbm.ia51.activities.ActivityType;
import fr.utbm.ia51.graph.human.GraphHuman;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;

public class GraphEnvironment extends EnvironmentEntity {
	private ArrayList<EnvironmentEntity> artifacts = new ArrayList<>();
	private SoccerField soccerField;
	private Bench bench;
	private BasketCourt basketCourt;
	private Forest forest;
	private TennisTable tennisTable;
	private Library library;
	private Toilet toilet;
	private Courtyard courtyard;
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

		
		// Seems useless to me to have a courtyard, isn't it? 
		/*this.courtyard= new Courtyard(width/3, height);
		this.courtyard.setTranslateX(-(width/3));
		this.getChildren().addAll(courtyard);
		this.artifacts.add(this.courtyard);*/
		
		double w, h = 0;
		
		w = 572*wFactor;
		h = 320*hFactor;
		this.soccerField = new SoccerField(w, h);
		this.add(this.soccerField, w, h, 1024, 576);

		w = 257*wFactor;
		h = 369*hFactor;
		this.basketCourt= new BasketCourt(w, h);
		this.add(this.basketCourt, w, h, 730, 0);

		w = 549*wFactor;
		h = 432*hFactor;
		this.forest= new Forest(w, h);
		this.add(this.forest, w, h, 1050, 0);

		w = 495*wFactor;
		h = 220*hFactor;
		this.library= new Library(w, h);
		this.add(this.library, w, h, 492, 680);

		w = 218*wFactor;
		h = 127*hFactor;
		this.toilet= new Toilet(w, h);
		this.add(this.toilet, w, h, 280, 0);

		w = 112*wFactor;
		h = 208*hFactor;
		this.classline= new ClassLine(w, h);
		this.add(this.classline, w, h, 27, 422);

		w = 342*wFactor;
		h = 57*hFactor;
		this.tennisTable= new TennisTable(w, h);
		this.add(this.tennisTable, w, h, 106, 803);
		
		
		this.bench = new Bench(30);
		this.bench.setTranslateX(-30);
		this.bench.setTranslateY(0);
		this.getChildren().addAll(bench);
		this.artifacts.add(this.bench);
		
		this.ringButton = new Button();
		ImageView image = new ImageView(new Image("file:src/main/resources/graphism/image/bell.png"));
		image.setPreserveRatio(false);
		image.setFitHeight(40);
		image.setFitWidth(40);
		this.ringButton.setGraphic(image);
//		this.ringButton.setTranslateX(-(width/2)+60);
//		this.ringButton.setTranslateY((height/2)-60);
		
				
//		this.ringButton.setOnMouseClicked(e->{
//			this.basketCourt.setVisible(false);
//		});



		
		
	}
	
	public void add(EnvironmentEntity e, double w, double h, double x, double y) {
		e.setTranslateX(-this.width/2.0 + x*wFactor + w/2.0);
		e.setTranslateY(-this.height/2.0 + y*hFactor + h/2.0);
		/*
		e.setTranslateX((x*wFactor)/2.0);
		e.setTranslateY((y*hFactor)/2.0);*/
		this.getChildren().addAll(e);
		this.artifacts.add(e);
	}
	
	public void printAllArtifactsPositions() {
		for(StackPane s : this.artifacts)
			System.out.println("Artifact "+s.getClass().getName()+" x= "+s.getTranslateX()+" y= "+s.getTranslateY());
	}
	
	
	public ArrayList<EnvironmentEntity> getIntersectedArtifacts(GraphHuman graphHuman) {
		
		
		ArrayList<EnvironmentEntity> intersectArtifacts = new ArrayList<>();
		for(EnvironmentEntity s : this.artifacts) {
			if(s.getBoundsInParent().intersects(graphHuman.getBoundsInParent())) {
				
//				System.out.println("Intersect with "+s.getClass().getName());
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

	public Button getRingButton() {
		return ringButton;
	}

	public void setRingButton(Button ringButton) {
		this.ringButton = ringButton;
	}
	
	
}
