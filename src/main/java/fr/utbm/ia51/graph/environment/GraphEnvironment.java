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
