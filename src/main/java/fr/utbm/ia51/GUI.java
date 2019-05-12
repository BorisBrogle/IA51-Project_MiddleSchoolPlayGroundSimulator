package fr.utbm.ia51;

import java.util.concurrent.CountDownLatch;

import fr.utbm.ia51.graph.environment.GraphEnvironment;
import fr.utbm.ia51.graph.human.GraphHuman;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GUI extends Application {
	
	public static final CountDownLatch latch = new CountDownLatch(1);
	public static GUI gui = null;
	
	final int WIDTH = 1280;
	final int HEIGHT = 720;

    public static void main(String[] args) {
        Application.launch(GUI.class, args);
    }
    
    public static GUI waitForGUIStart() {
    	try {
    		latch.await();
    	}catch (InterruptedException e) {
			e.printStackTrace();
		}
    	return gui;
    }
    
    public static void setGUI(GUI gui0) {
    	gui=gui0;
    	latch.countDown();
    }
    
    public GUI() {
    	setGUI(this);
    }
    
    @Override
    public void start(Stage primaryStage) {
    	
    	
        primaryStage.setTitle("Hello World");
        Group root = new Group();
        Scene scene = new Scene(root, this.WIDTH, this.HEIGHT, Color.LIGHTGREEN);
        
//        root.getChildren().add(new GraphHuman(1000, 300, "", "", 3,""));
        GraphEnvironment environment = new GraphEnvironment(Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());
        root.getChildren().add(environment);

        GraphHuman buddy = new GraphHuman(300, 300, "", "", 2,null,environment); 
        root.getChildren().add(buddy);
       
        buddy.moveTo(100, 100, 0.5);
        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println("pouet");
    }
    
    public void printBobo() {
    	System.out.println("Bobo l'asticot");
    }
}
