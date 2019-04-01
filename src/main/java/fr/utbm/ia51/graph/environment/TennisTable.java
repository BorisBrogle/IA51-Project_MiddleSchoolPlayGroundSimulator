package fr.utbm.ia51.graph.environment;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class TennisTable extends StackPane{
	
	private ImageView tennisTable;
	private Image fieldImage;
		
	
	public TennisTable(int width) {
		super();
				
		this.fieldImage = new Image("file:src/main/resources/graphism/image/table-tennis-01.png");
		this.tennisTable = new ImageView();
        tennisTable.setImage(this.fieldImage);
        
        tennisTable.setFitWidth(width);
        tennisTable.setPreserveRatio(true);

        this.setMaxSize(this.tennisTable.getFitWidth(), this.tennisTable.getFitHeight());
        this.setStyle("-fx-border-color : pink");
        
        this.getChildren().addAll(tennisTable);
	}
}

