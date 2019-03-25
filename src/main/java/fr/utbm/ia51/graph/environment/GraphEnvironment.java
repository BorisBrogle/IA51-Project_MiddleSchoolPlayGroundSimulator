package fr.utbm.ia51.graph.environment;

import javafx.scene.layout.StackPane;

public class GraphEnvironment extends StackPane {
	
	private SoccerField soccerField;
	
	public GraphEnvironment(int width, int height){
		super();
		this.setPrefSize(width, height);
		this.setStyle("-fx-border-color : blue");
		this.soccerField = new SoccerField(300, 100);
		this.getChildren().addAll(soccerField);
	}
}
