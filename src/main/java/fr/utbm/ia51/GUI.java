package fr.utbm.ia51;

import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;

import fr.utbm.ia51.boot.Boot;
import fr.utbm.ia51.graph.environment.GraphEnvironment;
import fr.utbm.ia51.graph.human.GraphHuman;
import fr.utbm.ia51.tools.PseudoRandomGenerator;
import io.sarl.bootstrap.SRE;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GUI extends Application {
	public static final CountDownLatch latch = new CountDownLatch(1);
	public static GUI gui = null;
	public LinkedList<GraphHuman> graphHumans = new LinkedList<>();
	
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
    	
    	
        primaryStage.setTitle("High School Playground Simulator");
        Group root = new Group();
        Scene scene = new Scene(root, this.WIDTH, this.HEIGHT, Color.LIGHTGREEN);
        
//        root.getChildren().add(new GraphHuman(1000, 300, "", "", 3,""));
        GraphEnvironment environment = new GraphEnvironment(Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());
        root.getChildren().add(environment);

        GraphHuman buddy = new GraphHuman(300, 300, "", "", 2,null,environment); 
        GraphHuman bob = new GraphHuman(200,200,"","",2,null,environment);
        root.getChildren().add(buddy);
        root.getChildren().add(bob);
        this.graphHumans.add(bob);
        this.graphHumans.add(buddy);
       
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setMaximized(true);
        
        try {
			SRE.getBootstrap().startAgent(Boot.class, this.graphHumans);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @Override
    public void stop() throws Exception {
    	
    }
    

}
