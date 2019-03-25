package fr.utbm.ia51;

import fr.utbm.ia51.graph.human.GraphHuman;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	
	final int WIDTH = 1280;
	final int HEIGHT = 720;

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World");
        Group root = new Group();
        Scene scene = new Scene(root, this.WIDTH, this.HEIGHT, Color.LIGHTGREEN);
        
        root.getChildren().add(new GraphHuman(1000, 300, "", "", 3));
       
        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println("pouet");
    }
}