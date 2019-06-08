package fr.utbm.ia51;

import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;

import fr.utbm.ia51.boot.Boot;
import fr.utbm.ia51.graph.environment.GraphEnvironment;
import fr.utbm.ia51.graph.human.GraphHuman;
import io.sarl.bootstrap.SRE;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
        primaryStage.setTitle("High School Playground Simulator - seed "+Globals.randomGenerator.getSeed());
        Pane root = new Pane();
        Scene scene = new Scene(root, this.WIDTH, this.HEIGHT, Color.TRANSPARENT);
        
        BackgroundImage bg = new BackgroundImage(
        						new Image("file:src/main/resources/graphism/image/background.png", this.WIDTH, this.HEIGHT, false, true),
                				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(bg));
        
        
        GraphEnvironment environment = new GraphEnvironment(this.WIDTH, this.HEIGHT);
        root.getChildren().add(environment);

        // We add two dummy Persons for our tests
        GraphHuman buddy = new GraphHuman(300, 300, "", "", 2, null, environment); 
        GraphHuman bob = new GraphHuman(200, 200, "", "", 2, null, environment);
        root.getChildren().add(buddy);
        root.getChildren().add(bob);
        this.graphHumans.add(bob);
        this.graphHumans.add(buddy);
        
        root.getChildren().add(environment.getRingButton());
       
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //primaryStage.setMaximized(true);
        
        addMouseClickCoords(scene);
        
        try {
			SRE.getBootstrap().startAgent(Boot.class, this.graphHumans);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    /*
     * If called, this function allows the user to click anywhere in the scene to know its position
     */
    protected void addMouseClickCoords(Scene scene) {
    	//Creating the mouse event handler 
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() { 
           @Override 
           public void handle(MouseEvent e) { 
              System.out.println("Clicked at: x="+e.getX()+" y="+e.getY()); 
           } 
        };   
        //Adding event Filter 
        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
    }
    
    
    @Override
    public void stop() throws Exception {}
}
