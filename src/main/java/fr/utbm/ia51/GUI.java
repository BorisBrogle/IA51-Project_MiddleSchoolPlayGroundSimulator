package fr.utbm.ia51;

import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;

import com.sun.xml.internal.bind.v2.runtime.Coordinator;

import fr.utbm.ia51.boot.Boot;
import fr.utbm.ia51.graph.environment.GraphEnvironment;
import fr.utbm.ia51.graph.human.GraphHuman;
import io.sarl.bootstrap.SRE;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI extends Application {
	public static final CountDownLatch latch = new CountDownLatch(1);
	public static GUI gui = null;
	public LinkedList<GraphHuman> graphHumans = new LinkedList<>();
	
	final int WIDTH = Globals.WIDTH;
	final int HEIGHT = Globals.HEIGHT;

	private CheckBox coordCheckBox = new CheckBox();
	private CheckBox vectorCheckBox = new CheckBox();
	private CheckBox viewfieldCheckBox = new CheckBox();
	
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
        
        
        VBox controlVBox = new VBox();
        controlVBox.setSpacing(5);
        controlVBox.setAlignment(Pos.CENTER);
        controlVBox.getChildren().add(environment.getRingButton());
        
        
        VBox coordVBox = new VBox();
        coordVBox.setAlignment(Pos.CENTER);
        coordCheckBox.selectedProperty().addListener((obs,old,val)->{
        	for(GraphHuman g : graphHumans) {
        		if(val)
        			g.getCoordinatesLabel().setVisible(true);
        		else
        			g.getCoordinatesLabel().setVisible(false);
        	}
        });
        coordCheckBox.setSelected(Globals.SHOW_AGENTS_COORDINATES);
        Label coordLabel = new Label("coordinates");
        coordLabel.setTextFill(Color.WHITE);
        coordVBox.getChildren().addAll(coordLabel,coordCheckBox);
        
        
 
        VBox vectVBox = new VBox();
        vectVBox.setAlignment(Pos.CENTER);
        vectorCheckBox.selectedProperty().addListener((obs,old,val)->{
        	for(GraphHuman g : this.graphHumans) {
        		if(val)
        			g.getForceArrow().setVisible(true);
        		else
        			g.getForceArrow().setVisible(false);
        	}
        	
        });
        vectorCheckBox.setSelected(Globals.SHOW_FORCE_VECTOR);
        Label vectorLabel = new Label("vector_direction");
        vectorLabel.setTextFill(Color.WHITE);
        vectVBox.getChildren().addAll(vectorLabel,vectorCheckBox);

        
        VBox viewfieldVBox = new VBox();
        viewfieldVBox.setAlignment(Pos.CENTER);
        viewfieldCheckBox.selectedProperty().addListener((obs,old,val)->{
        	for(GraphHuman g : this.graphHumans) {
        		if(val)
        			g.getViewField().setVisible(true);
        		else
        			g.getViewField().setVisible(false);
        	}
        });
        viewfieldCheckBox.setSelected(Globals.SHOW_VIEW_FIELD);
        Label viewfieldLabel = new Label("viewfield");
        viewfieldLabel.setTextFill(Color.WHITE);
        viewfieldVBox.getChildren().addAll(viewfieldLabel,viewfieldCheckBox);
        
        
        
        controlVBox.getChildren().addAll(coordVBox,vectVBox,viewfieldVBox);
        
        
        
        root.getChildren().add(controlVBox);
       
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        //primaryStage.setMaximized(true);
        
        //addMouseClickCoords(scene);
        
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
    public void stop() throws Exception {
    	System.out.println("--> Shutting down the application");
    	Globals.SHUT_DOWN_SIGNAL = true;
    }
}
