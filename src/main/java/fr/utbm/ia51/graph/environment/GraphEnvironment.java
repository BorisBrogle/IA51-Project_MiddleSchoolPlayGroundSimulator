package fr.utbm.ia51.graph.environment;

import java.util.ArrayList;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

public class GraphEnvironment extends EnvironmentEntity {
	
	
	
	private ArrayList<StackPane> artifacts = new ArrayList<>();
	private SoccerField soccerField;
	private Bench bench;
	private BasketCourt basketCourt;
	private Forest forest;
	private TennisTable tennisTable;
	private Library library;
	private Toilet toilet;
	private Courtyard courtyard;
	private ClassLine classline;
	
	public GraphEnvironment(double width, double height){
		super();
		this.setPrefSize(width, height);
		this.setStyle("-fx-border-color : blue");
		
		this.courtyard= new Courtyard(width/3, height);
		this.courtyard.setTranslateX(-(width/3));
		this.getChildren().addAll(courtyard);
		this.artifacts.add(this.courtyard);
		
		
		this.soccerField = new SoccerField(400);
		this.soccerField.setTranslateX((width/2)-260);
		this.soccerField.setTranslateY((height/2)-200);
		this.getChildren().addAll(soccerField);
		this.artifacts.add(this.soccerField);

		
		this.bench = new Bench(30);
		this.bench.setTranslateX(-30);
		this.bench.setTranslateY((height/2)-50);
		this.getChildren().addAll(bench);
		this.artifacts.add(this.bench);

		
		this.basketCourt= new BasketCourt(200);
		this.basketCourt.setTranslateX(150);
		this.basketCourt.setTranslateY(-(height/2)+100);
		Rotate rotate = new Rotate();  
		rotate.setAngle(90);
		this.basketCourt.getTransforms().add(rotate); 
		this.getChildren().addAll(basketCourt);
		this.artifacts.add(this.basketCourt);

		
		this.forest= new Forest(500);
		this.forest.setTranslateX((width/2)-250);
		this.forest.setTranslateY(-(height/2)+150);
		this.getChildren().addAll(forest);
		this.artifacts.add(this.forest);

		
		this.tennisTable= new TennisTable(50);
		this.tennisTable.setTranslateX(-(width/3));
		this.tennisTable.setTranslateY((height/2)-50);
		this.getChildren().addAll(tennisTable);
		this.artifacts.add(this.tennisTable);
		
		this.library= new Library(200);
		this.library.setTranslateX(100);
		this.library.setTranslateY((height/2)-90);
		this.getChildren().addAll(library);
		this.artifacts.add(this.library);
		
		this.toilet= new Toilet(200);
		this.toilet.setTranslateX(-(width/4));
		this.toilet.setTranslateY(-(height/2)+60);
		this.getChildren().addAll(toilet);
		this.artifacts.add(this.toilet);

		
		this.classline= new ClassLine(200);
		this.classline.setTranslateX(-(width/4));
		this.classline.setTranslateY(-(height/2)+250);
		this.getChildren().addAll(classline);
		this.artifacts.add(this.classline);
		
	}
	
	public void printAllArtifactsPositions() {
		for(StackPane s : this.artifacts)
			System.out.println("Artifact "+s.getClass().getName()+" x= "+s.getTranslateX()+" y= "+s.getTranslateY());
	}
	
	
	public ArrayList<StackPane> getIntersectedArtifacts(Rectangle area) {
		
		ArrayList<StackPane> intersectArtifacts = new ArrayList<>();
		for(StackPane s : this.artifacts) {
			if(s.intersects(area.getBoundsInLocal())) {
				System.out.println("Intersect with "+s.getClass().getName());
				intersectArtifacts.add(s);
			}
		}
		return intersectArtifacts;
		
	}
}
