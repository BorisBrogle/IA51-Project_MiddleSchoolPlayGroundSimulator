package fr.utbm.ia51.graph.environment;

import javafx.scene.layout.StackPane;
import javafx.scene.transform.Rotate;

public class GraphEnvironment extends StackPane {
	
	private SoccerField soccerField;
	private Bench bench;
	private BasketCourt basketCourt;
	private Forest forest;
	private TennisTable tennisTable;
	private Library library;
	private Toilet toilet;
	private Courtyard courtyard;
	private ClassLine classline;
	
	public GraphEnvironment(int width, int height){
		super();
		this.setPrefSize(width, height);
		this.setStyle("-fx-border-color : blue");
		
		this.courtyard= new Courtyard(width/3, height);
		this.courtyard.setTranslateX(-(width/3));
		this.getChildren().addAll(courtyard);
		
		this.soccerField = new SoccerField(200);
		this.soccerField.setTranslateX((width/2)-130);
		this.soccerField.setTranslateY((height/2)-100);
		this.getChildren().addAll(soccerField);
		
		this.bench = new Bench(30);
		this.bench.setTranslateX(-30);
		this.bench.setTranslateY((height/2)-20);
		this.getChildren().addAll(bench);
		
		this.basketCourt= new BasketCourt(200);
		this.basketCourt.setTranslateX(150);
		this.basketCourt.setTranslateY(-(height/2)+100);
		Rotate rotate = new Rotate();  
		rotate.setAngle(90);
		this.basketCourt.getTransforms().add(rotate); 
		this.getChildren().addAll(basketCourt);
		
		this.forest= new Forest(500);
		this.forest.setTranslateX((width/2)-250);
		this.forest.setTranslateY(-(height/2)+150);
		this.getChildren().addAll(forest);
		
		this.tennisTable= new TennisTable(50);
		this.tennisTable.setTranslateX(-300);
		this.tennisTable.setTranslateY((height/2)-50);
		this.getChildren().addAll(tennisTable);
		
		this.library= new Library(200);
		this.library.setTranslateX(100);
		this.library.setTranslateY((height/2)-90);
		this.getChildren().addAll(library);
		
		this.toilet= new Toilet(200);
		this.toilet.setTranslateX(-350);
		this.toilet.setTranslateY(-(height/2)+60);
		this.getChildren().addAll(toilet);
		
		this.classline= new ClassLine(200);
		this.classline.setTranslateX(-450);
		this.classline.setTranslateY(-(height/2)+250);
		this.getChildren().addAll(classline);
	}
}
